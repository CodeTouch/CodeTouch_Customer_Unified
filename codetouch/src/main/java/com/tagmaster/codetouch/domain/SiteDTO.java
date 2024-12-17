package com.tagmaster.codetouch.domain;

import com.nimbusds.oauth2.sdk.id.Identifier;
import lombok.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.w3c.dom.Text;

@Getter
@Setter
@ToString
public class SiteDTO {
    private int site_id;
    private int user_id;
    private String name;
    private String favicon;
    private String main_image;
    private int pay_state;
}


