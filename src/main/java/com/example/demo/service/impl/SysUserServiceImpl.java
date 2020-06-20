package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysAccount;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Title:
 * @Package
 * @Description:
 * @Author gyf
 * @Date 2020/6/20 12:56
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserByName(String userName) {
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().select(SysUser::getUserName).eq(SysUser::getId, 1));

        return sysUser;
    }
    public SysUser addSysUser(SysUser sysUser){
        return null;
    }
}
