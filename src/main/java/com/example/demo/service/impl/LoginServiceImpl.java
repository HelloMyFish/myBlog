package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.constant.CommonEnum;
import com.example.demo.constant.Constant;
import com.example.demo.constant.ConstantCode;
import com.example.demo.dao.SysAccountMapper;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysAccount;
import com.example.demo.entity.SysUser;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.service.LoginService;
import com.example.demo.util.encryption.MD5Util;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gyf
 * @date 2020/6/20 18:29
 */
public class LoginServiceImpl implements LoginService {
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
            throw new RuntimeException("不存在此账号，请重试!");
        }
        String salt = sysAccount.getSalt();
        String md5Password = MD5Util.md5(password + salt);
        if(md5Password.equals(sysAccount.getMd5Password())){
            R r = new R();
            r.setCode(ConstantCode.SUCCESS_CODE.value());
            r.setMsg("欢迎您，登陆成功!");
            return r;
        }else{
            throw new RuntimeException("登陆失败!");
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
        sysUser.setIsDeleted(CommonEnum.NOT_DELETED.value());
        sysUser.setCreateTime(new Date());
        sysUser.setCreateUserId(1L);
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUserId(1L);
        sysUserMapper.insert(sysUser);

        //向账户表插入数据
        SysAccount insertAccount = new SysAccount();
        insertAccount.setSalt(MD5Util.salt());
        insertAccount.setMd5Password(MD5Util.md5(registerModel.getPassword()+sysAccount.getSalt()));
        insertAccount.setUserName(registerModel.getUserName());
        insertAccount.setCreateTime(new Date());
        insertAccount.setCreateUserId(1L);
        insertAccount.setUpdateTime(new Date());
        insertAccount.setUpdateUserId(1L);
        insertAccount.setUserId(sysUser.getId());
        sysAccountMapper.insert(insertAccount);
        return new R().setCode(ConstantCode.SUCCESS_CODE.value());
    }

}
