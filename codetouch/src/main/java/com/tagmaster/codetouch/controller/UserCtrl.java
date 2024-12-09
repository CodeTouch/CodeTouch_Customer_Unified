package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class UserCtrl {
    UserSvc userSvc;

    @Autowired
    public UserCtrl(UserSvc userSvc){
        this.userSvc=userSvc;
    }
}
