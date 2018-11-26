package com.quick.quickhybrid.api;

import android.webkit.WebView;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestPayMethod implements IApiMethod {
    @Override
    public String name() {
        return "testMethod";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "pay success");
        callback.applySuccess(map);
    }
}
