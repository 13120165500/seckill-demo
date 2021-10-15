package com.mall.seckill.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("t_good")
public class Good extends Model<Good> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 商品库存,-1表示没有限制
     */
    private Integer goodStock;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
