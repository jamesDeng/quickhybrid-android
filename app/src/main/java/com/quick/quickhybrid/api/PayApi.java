package com.quick.quickhybrid.api;

import android.webkit.WebView;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dailichun on 2017/12/6.
 * 测试的支付组件（自定义）API
 */
public class PayApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "pay";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        List<IApiMethod> result = new ArrayList<>();
        result.add(new TestPayMethod());
        return result;
    }
}
