package com.example.demo.sys.service;

import com.example.demo.model.ResultData;
import com.example.demo.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gyf
 * @since 2020-06-21
 */
public interface ISysUserService extends IService<SysUser> {
    public SysUser queryUserByName(String userName);

    ResultData modifyEmail(Long userId, String email);

    ResultData confirmEmail(Long userId, String email);

    ResultData sendEmail(Long userId);

    SysUser queyrByusername(String username);
}
