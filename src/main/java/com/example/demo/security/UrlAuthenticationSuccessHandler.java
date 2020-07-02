package com.example.demo.security;

import com.example.demo.model.ResultData;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.service.ISysUserService;
import com.example.demo.sys.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gyf
 * @date 2020/6/27 23:03
 */
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ISysUserService iSysUserService;

    private static final Logger logger = LoggerFactory.getLogger(UrlAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        ResultData<Object> objectResultData = ResultData.ofSuccess();

        String username = (String) authentication.getPrincipal(); //表单输入的用户名
        String password = (String) authentication.getCredentials(); //表单输入的密码
        SysUser sysUser = iSysUserService.queyrByusername(username);
        Map<String, Object> userInfo = userService.findMenuInfoByUsername(username, response);

        response.setData(userInfo);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(response));
    }
}
