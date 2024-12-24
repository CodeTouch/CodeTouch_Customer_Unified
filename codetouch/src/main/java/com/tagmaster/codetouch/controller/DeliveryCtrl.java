package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.DeliveryDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/고객")
public class DeliveryCtrl {
    private final DeliverySvc deliverySvc;
    @Autowired
    public DeliveryCtrl(DeliverySvc deliverySvc){
        this.deliverySvc = deliverySvc;
    }
    @PostMapping("/주문/옵션설정")
    public String deliveryOptionSetting(@ModelAttribute DeliveryDTO deliveryDTO){
        try{
            return deliverySvc.optionSetting(deliveryDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
