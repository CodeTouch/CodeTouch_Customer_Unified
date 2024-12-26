package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DeliveryDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DeliveryMapper {
//    @Insert("INSERT INTO delivery (del_id, min_price, orderAutoExpiry, description ) VALUES ( #{del_id}, #{min_price}, #{orderAutoExpiry}, #{description})")
//    int setDeliverySetting(DeliveryDTO deliveryDTO);
    @Update("UPDATE delivery SET min_price=#{min_price}, orderAutoExpiry=#{orderAutoExpiry}, description=#{description} WHERE site_id=#{site_id}")
    int updateDeliverySetting(int site_id);
//    @Delete("DELETE FROM delivery WHERE del_id=#{del_id}");
//    int deleteDeliverySetting(int del_id);
}
