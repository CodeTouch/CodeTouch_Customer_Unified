package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MailSendDTO {
    private int site_id;
    private LocalDateTime send_at;
    private String content;
}
