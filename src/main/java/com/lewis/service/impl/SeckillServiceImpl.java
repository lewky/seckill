package com.lewis.service.impl;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.lewis.dao.SeckillDao;
import com.lewis.dao.SuccessKilledDao;
import com.lewis.dao.cache.RedisDao;
import com.lewis.dto.Exposer;
import com.lewis.dto.SeckillExecution;
import com.lewis.entity.Seckill;
import com.lewis.entity.SuccessKilled;
import com.lewis.enums.SeckillStatEnum;
import com.lewis.exception.RepeatKillException;
import com.lewis.exception.SeckillCloseException;
import com.lewis.exception.SeckillException;
import com.lewis.service.SeckillService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component @Service @Repository @Controller
@Service
public class SeckillServiceImpl implements SeckillService {
	// 日志对象
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 加入一个混淆字符串(秒杀接口)的salt，为了避免用户猜出我们的md5值，值任意给，越复杂越好
	private final String salt = "aksehiucka24sf*&%&^^#^%$";

	// 注入Service依赖
	@Autowired
	// @Resource
	private SeckillDao seckillDao;

	@Autowired
	// @Resource
	private SuccessKilledDao successKilledDao;

	 @Autowired
	 private RedisDao redisDao;

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// 优化点:缓存优化:超时的基础上维护一致性
		// 1.访问redi

		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			// 2.访问数据库
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {// 说明查不到这个秒杀产品的记录
				return new Exposer(false, seckillId);
			} else {
				// 3.放入redis
				redisDao.putSeckill(seckill);
			}
		}

		// 若是秒杀未开启
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		// 秒杀开启，返回秒杀商品的id、用给接口加密的md5
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	// 秒杀是否成功，成功:减库存，增加明细；失败:抛出异常，事务回滚
	@Transactional
	/**
	 * 使用注解控制事务方法的优点:
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作、只读操作不要事务控制
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,
			RepeatKillException, SeckillCloseException {

		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");// 秒杀数据被重写了
		}
		// 执行秒杀逻辑:减库存+增加购买明细
		Date nowTime = new Date();

		try {

			// 否则更新了库存，秒杀成功,增加明细
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			// 看是否该明细被重复插入，即用户是否重复秒杀
			if (insertCount <= 0) {
				throw new RepeatKillException("seckill repeated");
			} else {

				// 减库存,热点商品竞争
				int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
				if (updateCount <= 0) {
					// 没有更新库存记录，说明秒杀结束 rollback
					throw new SeckillCloseException("seckill is closed");
				} else {
					// 秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息 commit
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 将编译期异常转化为运行期异常
			throw new SeckillException("seckill inner error :" + e.getMessage());
		}

	}
	
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			return new SeckillExecution(seckillId, SeckillStatEnum.DATE_REWRITE);
		}
		Date killTime = new Date();
		Map<String, Object> map = new HashMap<>();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", killTime);
		map.put("result", null);
		// 执行储存过程,result被复制
		seckillDao.killByProcedure(map);
		// 获取result
		int result = MapUtils.getInteger(map, "result", -2);
		if (result == 1) {
			SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
			return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
		} else {
			return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
		}
	}
}
