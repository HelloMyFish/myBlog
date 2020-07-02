package com.example.demo.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.constant.BusinessErrorCode;
import com.example.demo.constant.DeletedConstant;
import com.example.demo.model.ResultData;
import com.example.demo.module.sys.entity.SysAccount;
import com.example.demo.module.sys.entity.SysUser;
import com.example.demo.module.sys.mapper.SysAccountMapper;
import com.example.demo.module.sys.mapper.SysUserMapper;
import com.example.demo.module.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.RandomCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Override
    public SysUser queryUserByName(String userName) {
        SysAccount sysAccount = sysAccountMapper.selectOne(new LambdaQueryWrapper<SysAccount>().select(SysAccount::getUserId)
                .eq(SysAccount::getIsDeleted, DeletedConstant.NOT_DELETED.value()).eq(SysAccount::getUserName, userName));
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, sysAccount.getUserId()));
    }

    @Transactional
    @Override
    public ResultData modifyEmail(Long userId, String email) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUserEmail(email);
        int i = sysUserMapper.updateById(sysUser);
        logger.info("userId:{}", userId);
        if(i>0){
            return ResultData.ofSuccess();
        }else{
            logger.info("更新失败，未找到用户！");
            return ResultData.ofFail(BusinessErrorCode.BUSINESS_ERROR);
        }
    }

    @Override
    public ResultData confirmEmail(Long userId, String email) {
        if(StringUtils.isBlank(email)){
            throw new RuntimeException("邮箱不允许为空");
        }
        SysUser sysUser = sysUserMapper.selectById(userId);
        if(sysUser==null){
            throw new RuntimeException("错误的用户");
        }else{
            if (email.equals(sysUser.getUserEmail())) {
                return ResultData.ofSuccess();
            }else{
                return ResultData.ofFail("ERROR_MAIL_CODE","情输入正确的验证码！");
            }
        }
    }

    @Override
    public ResultData sendEmail(Long userId) {
        logger.info("userId:{}",userId);
        String code = RandomCodeUtils.produceCode(6);
        SysAccount sysAccount = new SysAccount();
        UpdateWrapper<SysAccount> eq = new UpdateWrapper<SysAccount>().eq("userId", userId);
        int update = sysAccountMapper.update(sysAccount, eq);
        if(update>0){
            return ResultData.ofSuccess();
        }else{
            return ResultData.ofFail(BusinessErrorCode.BUSINESS_ERROR);
        }
    }

    @Override
    public SysUser queyrByUsername(String username) {
        if (StringUtils.isNotBlank(username)) {
            
        }
        return null;
    }

    public SysUser addSysUser(SysUser sysUser){
        return null;
    }
}
