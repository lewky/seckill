package com.lewis.dao;

import org.apache.ibatis.annotations.Param;

import com.lewis.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复
	 * 
	 * @param seckillId
	 * @param userphone
	 * @return 插入的行数，如果返回值<1则表示插入失败
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * 根据id查询SuccessKilled并携带秒杀商品对象实体
	 * 
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
