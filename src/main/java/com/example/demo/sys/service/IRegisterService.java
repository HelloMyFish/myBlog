package com.example.demo.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;

/**
 * @author gyf
 * @date 2020/6/25 23:43
 */
public interface IRegisterService {
    /**
     *
     * @param registerModel 实体类
     * @return R
     */
    ResultData register(RegisterModel registerModel);
}
