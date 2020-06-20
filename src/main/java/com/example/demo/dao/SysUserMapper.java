package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser queryUserByName(String sysUser);
}