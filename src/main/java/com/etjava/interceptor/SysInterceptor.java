package com.etjava.interceptor;

import com.etjava.bean.CheckResult;
import com.etjava.bean.R;
import com.etjava.constant.SystemConstant;
import com.etjava.util.JwtUtils;
import com.etjava.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器
 */
public class SysInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(SysInterceptor.class);

    /**
     * 方法调用之前执行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getRequestURI();
        logger.info("访问路径："+contextPath);
        String token = request.getHeader("token");
        if(handler instanceof HandlerMethod){
            if(StringUtil.isEmpty(token)){
                logger.info("token不存在");
                print(response, R.error(SystemConstant.JWT_ERRCODE_NULL,"验证签名失败"));
                return false; // 不去执行方法
            }else{
                // 验证token
                CheckResult result = JwtUtils.validateJWT(token);
                if(result.isSuccess()){
                    logger.info("签名验证通过");
                    return true;
                }else{

                    switch (result.getErrCode()){
                        case SystemConstant.JWT_ERRCODE_FAIL:
                            logger.error("签名验证不通过");
                            print(response, R.error(SystemConstant.JWT_ERRCODE_FAIL,"验证签名失败"));
                            break;
                        case SystemConstant.JWT_ERRCODE_EXPIRE:
                            print(response, R.error(SystemConstant.JWT_ERRCODE_EXPIRE,"验证签名过期"));
                            logger.error("签名过期");
                            break;
                    }
                    return false;
                }
            }
        }else{
            return true;
        }
    }

    /*
    封装返回数据流
     */
    private void print(HttpServletResponse response, Object message){
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(message.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
