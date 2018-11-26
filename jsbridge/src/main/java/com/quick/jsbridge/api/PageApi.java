package com.quick.jsbridge.api;

import com.quick.jsbridge.api.method.page.PageCloseMethod;
import com.quick.jsbridge.api.method.page.PageGetTokenMethod;
import com.quick.jsbridge.api.method.page.PageOpenLocalMethod;
import com.quick.jsbridge.api.method.page.PageOpenMethod;
import com.quick.jsbridge.api.method.page.PageReloadMethod;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;

import java.util.Arrays;
import java.util.List;


/**
 * Created by dailichun on 2017/12/6.
 */
public class PageApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "page";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new PageOpenMethod(),
                new PageOpenLocalMethod(),
                new PageCloseMethod(),
                new PageGetTokenMethod(),
                new PageReloadMethod()
        );
    }
}
