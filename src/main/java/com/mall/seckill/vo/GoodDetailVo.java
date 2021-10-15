package com.mall.seckill.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GoodDetailVo = Good + SeckillGood
 *  包含秒杀信息和商品基本信息
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDetailVo {
    /**
     * Good信息
     * */

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品标题
     */
    private String goodTitle;

    /**
     * 商品图片
     */
    private String goodImg;

    /**
     * 商品详情
     */
    private String goodDetail;

    /**
     * 商品价格
     */
    private BigDecimal goodPrice;

    /**
     * SeckillGood信息
     * */

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;
}
