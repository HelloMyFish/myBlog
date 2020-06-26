package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.constant.DeletedConstant;
import com.example.demo.sys.entity.SysAccount;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysAccountMapper;
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
    @Autowired
    private SysAccountMapper sysAccountMapper;

    @Override
    public SysUser queryUserByName(String userName) {
        SysAccount sysAccount = sysAccountMapper.selectOne(new LambdaQueryWrapper<SysAccount>().select(SysAccount::getUserId)
                .eq(SysAccount::getIsDeleted, DeletedConstant.NOT_DELETED.value()).eq(SysAccount::getUserName, userName));
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, sysAccount.getUserId()));
    }
    public SysUser addSysUser(SysUser sysUser){
        return null;
    }
}
