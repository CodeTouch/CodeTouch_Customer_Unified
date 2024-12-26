package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.DeliveryDTO;
import com.tagmaster.codetouch.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverySvc {
    DeliveryMapper deliveryMapper;

    @Autowired
    public DeliverySvc(DeliveryMapper deliveryMapper) {
        this.deliveryMapper = deliveryMapper;
    }

//    public String insertSetting(DeliveryDTO deliveryDTO) {
//        if (deliveryMapper.setDeliverySetting(deliveryDTO) > 0) {
//            return "success";
//        }
//        return "error";
//    }

    public String updateSetting(DeliveryDTO deliveryDTO) {
        if (deliveryMapper.updateDeliverySetting(deliveryDTO) > 0) {
            return "success";
        }
        return "error";
    }
}

