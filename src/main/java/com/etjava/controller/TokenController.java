package com.etjava.controller;

import com.etjava.bean.CheckResult;
import com.etjava.bean.R;
import com.etjava.constant.SystemConstant;
import com.etjava.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 刷新token 防止token拦截
 * 间隔时间短 即便是拦截到token 也是之前的token了 同样在验证签名时会失败
 */
@RestController
@RequestMapping("/")
public class TokenController {

    private Logger logger = LoggerFactory.getLogger(TokenController.class);

    /*
    刷新用户的token
     */
    @RequestMapping("/refresh")
    public R refreshToken(HttpServletRequest request) throws Exception{
        CheckResult result = JwtUtils.validateJWT(request.getHeader("token"));
        Claims claims = result.getClaims();
        String token = JwtUtils.createJWT(String.valueOf(claims.getId()), claims.getSubject(), SystemConstant.JWT_TTL);
        R r = new R();
        r.put("token",token);
        logger.info("新token:"+token);
        return r;
    }
}
