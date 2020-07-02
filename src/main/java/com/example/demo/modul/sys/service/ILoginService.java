package com.example.demo.modul.sys.service;

import com.example.demo.model.ResultData;
import com.example.demo.modul.sys.model.LoginInputModel;

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
