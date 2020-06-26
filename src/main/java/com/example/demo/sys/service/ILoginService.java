package com.example.demo.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.sys.model.LoginInputModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gyf
 * @date 2020/6/20 18:29
 */
public interface ILoginService {
    /**
     *
     * @param model 入参对象
     * @param request request
     * @return R
     */
    public ResultData login(LoginInputModel model, HttpServletRequest request);

}
