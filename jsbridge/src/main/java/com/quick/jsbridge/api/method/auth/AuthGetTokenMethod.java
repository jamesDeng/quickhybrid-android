package com.quick.jsbridge.api.method.auth;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.application.FrmApplication;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取token值
 * <p>
 * 返回：
 * access_token
 */
public class AuthGetTokenMethod implements IApiMethod {

    @Override
    public String name() {
        return "getToken";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", "test-token-quickhybrid");
        callback.applySuccess(map);
    }
}
