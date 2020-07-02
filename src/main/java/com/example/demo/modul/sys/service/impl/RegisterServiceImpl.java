package com.example.demo.modul.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.constant.DeletedConstant;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.modul.sys.entity.SysAccount;
import com.example.demo.modul.sys.entity.SysUser;
import com.example.demo.modul.sys.mapper.SysAccountMapper;
import com.example.demo.modul.sys.mapper.SysUserMapper;
import com.example.demo.modul.sys.service.IRegisterService;
import com.example.demo.util.encryption.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author gyf
 * @date 2020/6/25 23:44
 */
@Service
public class RegisterServiceImpl implements IRegisterService {
    @Autowired
    private SysAccountMapper sysAccountMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
    /**
     　　* @author gyf
     　　* @Description 实现注册功能
     　　* @param ${tags}
     　　* @return ${return_type}
     　　* @throws
     　　* @date 2020/6/20 22:37
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData register(RegisterModel registerModel){
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
        sysUser.setUserSex(registerModel.getSex());
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
        return ResultData.ofSuccess();
    }
}
