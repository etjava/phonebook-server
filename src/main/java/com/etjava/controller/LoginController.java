package com.etjava.controller;

import com.etjava.bean.Admin;
import com.etjava.bean.R;
import com.etjava.constant.SystemConstant;
import com.etjava.service.AdminService;
import com.etjava.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public R login(@RequestBody Admin admin) throws Exception{
        Admin user = adminService.login(admin);
        R r = new R();
        if(user == null){
            return r.error("用户名或密码错误");
        }else{
            String token = JwtUtils.createJWT(String.valueOf(user.getId()), user.getUserName(), SystemConstant.JWT_TTL);

            r.put("token",token);
            return r;
        }
    }
}
