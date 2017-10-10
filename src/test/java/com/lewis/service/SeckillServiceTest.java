package com.lewis.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lewis.dto.Exposer;
import com.lewis.dto.SeckillExecution;
import com.lewis.entity.Seckill;
import com.lewis.exception.RepeatKillException;
import com.lewis.exception.SeckillCloseException;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service实现类依赖
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetById() {
		long seckillId = 1000;
		Seckill seckill = seckillService.getById(seckillId);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long seckillId = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		logger.info("exposer={}", exposer);
	}

	@Transactional
	@Test
	public void testExecuteSeckill() {
		long seckillId = 1000;
		long userPhone = 13476191876L;
		String md5 = "70b9564762568e9ff29a4a949f8f6de4";

		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
			logger.info("result={}", execution);
		} catch (RepeatKillException e) {
			logger.error(e.getMessage());
		} catch (SeckillCloseException e1) {
			logger.error(e1.getMessage());
		}
	}

	// 集成测试代码完整逻辑，注意可重复执行
	@Transactional
	@Test
	public void testSeckillLogic() throws Exception {
		long seckillId = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if (exposer.isExposed()) {
			logger.info("exposer={}", exposer);
			long userPhone = 13476191576L;
			String md5 = exposer.getMd5();

			try {
				SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
				logger.info("result={}", execution);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SeckillCloseException e1) {
				logger.error(e1.getMessage());
			}
		} else {
			// 秒杀未开启
			logger.warn("exposer={}", exposer);
		}
	}
	
	@Test
	public void executeSeckillProcedure(){
		long seckillId = 1001;
        long phone = 13680115101L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info("execution={}", execution);
        }
	}
	
}
