package com.example.demo.module.sys.mapper;

import com.example.demo.module.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gyf
 * @since 2020-06-21
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser queryUserByName(String sysUser);
}
