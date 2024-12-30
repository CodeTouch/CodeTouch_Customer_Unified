package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DeliveryDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeliveryMapper {
//    @Insert("INSERT INTO delivery (del_id, min_price, orderAutoExpiry, description ) VALUES ( #{del_id}, #{min_price}, #{orderAutoExpiry}, #{description})")
//    int setDeliverySetting(DeliveryDTO deliveryDTO);
    //배송 기본 설정
    @Update("UPDATE setting SET min_price=#{min_price}, order_auto_cancel=#{order_auto_cancel}, description=#{description}, not_user_order=#{not_user_order} WHERE site_id=#{site_id}")
    int updateDeliverySetting(int site_id);
    @Select("SELECT st.site_name, s.min_price, s.order_auto_cancel, s.description, s.not_user_order FROM setting s JOIN site st ON st.site_id=s.site_id WHERE s.site_id=#{site_id}")
    DeliveryDTO getDeliveryBySiteId(int site_id);
//    @Delete("DELETE FROM delivery WHERE del_id=#{del_id}");
//    int deleteDeliverySetting(int del_id);
}
