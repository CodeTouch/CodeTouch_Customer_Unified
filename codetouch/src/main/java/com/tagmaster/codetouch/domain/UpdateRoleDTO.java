package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRoleDTO {
    private int site_id;
    private String email;
    private String role;
}
