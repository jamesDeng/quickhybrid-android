package com.quick.jsbridge.api.method.runtime;

import android.webkit.WebView;

import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.BuildConfig;

/**
 * 获取Quick版本号
 * <p>
 * 返回：
 * version
 */
public class GetQuickVersionMethod implements IApiMethod {

    @Override
    public String name() {
        return "getQuickVersion";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> object = new HashMap<>();
        object.put("version", BuildConfig.VERSION_NAME);
        callback.applySuccess(object);
    }
}
