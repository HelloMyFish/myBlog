package com.example.demo.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;

/**
 * @author gyf
 * @date 2020/6/20 18:29
 */
public interface ILoginService {
    public R login(String userName,String password);

    R register(RegisterModel registerModel);
}
