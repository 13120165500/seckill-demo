package com.mall.seckill.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodId;

    /**
     * 收货地址ID
     */
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private Integer goodCount;

    /**
     * 商品单价
     */
    private BigDecimal goodPrice;

    /**
     * 1-pc,2-android,3-ios
     */
    private Integer orderChannel;

    /**
     * 订单状态,0-新建未支付,1-已支付,2-已发货,3-已收货,4-已退款,5-已完成,6-已关闭
     */
    private Integer status;

    /**
     * 订单的创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
