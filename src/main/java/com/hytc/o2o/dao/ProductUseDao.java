package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductUse;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductUseDao {

    @Select("SELECT " +
            "t2.product_name AS productName," +
            "t1.create_time AS createTime," +
            "t3.name AS userName," +
            "t1.point as count "+
            "FROM " +
            "tb_user_product_map t1 " +
            "LEFT JOIN " +
            "tb_product t2 " +
            "ON " +
            "t1.`product_id` = t2.`product_id`" +
            "LEFT JOIN " +
            "tb_person_info t3 " +
            "ON " +
            "t1.`user_id` = t3.`user_id` "+
            "ORDER BY t1.point DESC "+
            "LIMIT 0,9")
    List<ProductUse> getAll();
}
