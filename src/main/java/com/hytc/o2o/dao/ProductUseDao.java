package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductUse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductUseDao {

    @Select("SELECT " +
            "t2.product_name AS productName," +
            "t1.create_time AS createTime," +
            "t3.shop_name AS shopName," +
            "t1.total as count "+
            "FROM " +
            "tb_product_sell_daily t1 " +
            "LEFT JOIN " +
            "tb_product t2 " +
            "ON " +
            "t1.`product_id` = t2.`product_id`" +
            "LEFT JOIN " +
            "tb_shop t3 " +
            "ON " +
            "t1.`shop_id` = t3.`shop_id` "+
            "WHERE t1.`shop_id` = #{shopId} "+
            "ORDER BY t1.total DESC "+
            "LIMIT 0,9")
    List<ProductUse> getAll(@Param("shopId")String shopId);
}
