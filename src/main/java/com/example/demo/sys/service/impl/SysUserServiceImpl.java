package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysUserMapper;
import com.example.demo.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gyf
 * @since 2020-06-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
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
