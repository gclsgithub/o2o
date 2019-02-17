package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Records {

    /**
     * 获得所有的记录
     * @return
     */
    @Select("<script>"+
            "SELECT" +
            " t2.name AS userName," +
            " t3.product_name AS productName," +
            " t1.point AS point," +
            " t1.create_time AS createTime" +
            " FROM" +
            " tb_user_product_map t1" +
            " LEFT JOIN" +
            " tb_person_info t2" +
            " ON" +
            " t1.`user_id` = t2.`user_id`" +
            " LEFT JOIN" +
            " tb_product t3" +
            " ON" +
            " t3.`product_id` = t1.`product_id`" +
            " WHERE t1.shop_id = #{shopId}"+
            "<when test='useName != null '>AND t2.name = #{useName} </when>"+
            " ORDER BY" +
            " t1.point" +
            " LIMIT 0,9"+
            "</script>"
            )
    List<ProductRecord> getAllRecords(@Param("shopId")String shopId,@Param("useName")String useName);
}
