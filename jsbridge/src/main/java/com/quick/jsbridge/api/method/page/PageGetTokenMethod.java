package com.quick.jsbridge.api.method.page;

import android.webkit.WebView;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取 token
 */
public class PageGetTokenMethod implements IApiMethod {

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
