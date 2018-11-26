package com.quick.jsbridge.api;


import com.quick.jsbridge.api.method.auth.AuthConfigMethod;
import com.quick.jsbridge.api.method.auth.AuthGetTokenMethod;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;

import java.util.Arrays;
import java.util.List;


/**
 * Created by dailichun on 2017/12/6.
 * auth相关API
 */
public class AuthApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "auth";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new AuthConfigMethod(),
                new AuthGetTokenMethod()
        );
    }
}
