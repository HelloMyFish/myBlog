package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.config.exception.LoginException;
import com.example.demo.constant.DeletedConstant;
import com.example.demo.constant.ConstantCode;
import com.example.demo.model.RegisterModel;
import com.example.demo.sys.entity.SysAccount;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysAccountMapper;
import com.example.demo.sys.mapper.SysUserMapper;
import com.example.demo.sys.service.ILoginService;
import com.example.demo.util.encryption.MD5Util;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gyf
 * @date 2020/6/20 18:29
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private SysAccountMapper sysAccountMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public R login(String userName, String password) {
        LambdaQueryWrapper<SysAccount> wrapper = new LambdaQueryWrapper<SysAccount>()
                .select(SysAccount::getMd5Password, SysAccount::getSalt)
                .eq(SysAccount::getUserName, userName);
        SysAccount sysAccount = sysAccountMapper.selectOne(wrapper);
        if(sysAccount==null){
            throw new LoginException("不存在此账号，请重试!");
        }
        String salt = sysAccount.getSalt();
        String md5Password = MD5Util.md5(password + salt);
        if(md5Password.equals(sysAccount.getMd5Password())){
            R r = new R();
            r.setCode(ConstantCode.SUCCESS_CODE.value());
            r.setMsg("欢迎您，登陆成功!");
            return r;
        }else{
            throw new LoginException("登陆失败!");
        }
    }
    /**
    　　* @author gyf
    　　* @Description 实现注册功能
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @date 2020/6/20 22:37
    */
    @Override
    public R register(RegisterModel registerModel){
        String userName = registerModel.getUserName();
        LambdaQueryWrapper<SysAccount> wrapper = new LambdaQueryWrapper<SysAccount>().select(SysAccount::getId).eq(SysAccount::getUserName, userName);
        SysAccount sysAccount = sysAccountMapper.selectOne(wrapper);
        if(sysAccount!=null){
            throw new RuntimeException("当前用户名已被注册！");
        }
        //向用户表插入数据
        SysUser sysUser = new SysUser();
        sysUser.setTrueName(registerModel.getTrueName());
        sysUser.setUserEmail(registerModel.getEmail());
        sysUser.setUserMobile(registerModel.getMobile());
        sysUser.setIsDeleted(DeletedConstant.NOT_DELETED.value());
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setCreateUserId(1L);
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setUpdateUserId(1L);
        sysUserMapper.insert(sysUser);

        //向账户表插入数据
        SysAccount insertAccount = new SysAccount();
        String salt = MD5Util.salt();
        insertAccount.setSalt(salt);
        insertAccount.setMd5Password(MD5Util.md5(registerModel.getPassword()+salt));
        insertAccount.setUserName(registerModel.getUserName());
        insertAccount.setLastLoginIp(registerModel.getIpAddress());
        insertAccount.setLastLoginTime(LocalDateTime.now());
        insertAccount.setCreateTime(LocalDateTime.now());
        insertAccount.setCreateUserId(1L);
        insertAccount.setUpdateTime(LocalDateTime.now());
        insertAccount.setUpdateUserId(1L);
        insertAccount.setIsDeleted(DeletedConstant.NOT_DELETED.value());
        insertAccount.setUserId(sysUser.getId());
        sysAccountMapper.insert(insertAccount);
        return new R().setCode(ConstantCode.SUCCESS_CODE.value());
    }

}
