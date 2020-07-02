package com.example.demo.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.config.exception.LoginException;
import com.example.demo.model.ResultData;
import com.example.demo.module.sys.entity.SysAccount;
import com.example.demo.module.sys.entity.SysUser;
import com.example.demo.module.sys.mapper.SysAccountMapper;
import com.example.demo.module.sys.mapper.SysUserMapper;
import com.example.demo.module.sys.model.LoginInputModel;
import com.example.demo.module.sys.service.ILoginService;
import com.example.demo.util.encryption.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author gyf
 * @date 2020/6/20 18:29
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final DateTimeFormatter FORMATTER_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final int TODAY_MAX_LOGIN_TIMES = 3;
    @Autowired
    private SysAccountMapper sysAccountMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional(noRollbackFor = LoginException.class)
    @Override
    public ResultData login(LoginInputModel model,HttpServletRequest request) {
        LambdaQueryWrapper<SysAccount> wrapper = new LambdaQueryWrapper<SysAccount>()
                .eq(SysAccount::getUserName, model.getUserName());
        SysAccount sysAccount = sysAccountMapper.selectOne(wrapper);
        if(sysAccount==null){
            throw new LoginException("不存在此账号，请重试!");
        }
        if (sysAccount.getLastLoginTime()!=null && LocalDateTime.now().toLocalDate().equals(sysAccount.getLastLoginTime().toLocalDate())
                && sysAccount.getTodayLoginFailTimes()!=null && sysAccount.getTodayLoginFailTimes()>TODAY_MAX_LOGIN_TIMES) {
            throw new LoginException("今日登陆错误次数超过3次，请明日再试！");
        }
        String salt = sysAccount.getSalt();
        String md5Password = MD5Util.md5(model.getPassword() + salt);
        if(md5Password.equals(sysAccount.getMd5Password())){
            sysAccount.setLastLoginTime(LocalDateTime.now());
            sysAccount.setLastLoginIp(model.getIpAddress());
            sysAccount.setTodayLoginFailTimes(0);
            sysAccountMapper.updateById(sysAccount);
            Long userId = sysAccount.getUserId();
            SysUser sysUser = sysUserMapper.selectById(userId);
            if(sysUser==null){
                throw new LoginException("用户不存在！");
            }
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",sysUser);
//            R r = new R();
//            r.setCode(ConstantCode.SUCCESS_CODE.value());
//            r.setMsg("欢迎您，登陆成功!");
            return ResultData.ofSuccess();
        }else{
            sysAccount.setLastLoginTime(LocalDateTime.now());
            int tryTimes = TODAY_MAX_LOGIN_TIMES - sysAccount.getTodayLoginFailTimes();
            sysAccount.setTodayLoginFailTimes(sysAccount.getTodayLoginFailTimes() + 1);
            sysAccountMapper.updateById(sysAccount);
            throw new LoginException("密码错误！今日剩余尝试次数" + tryTimes);
        }
    }


}
