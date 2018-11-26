package com.quick.jsbridge.api.method.runtime;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

/**
 * 获取APP版本号
 * <p>
 * 返回：
 * version
 */
public class GetAppVersionMethod implements IApiMethod {

    @Override
    public String name() {
        return "getAppVersion";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> object = new HashMap<>();
        object.put("version", RuntimeUtil.getVersionName(webLoader.getPageControl().getActivity()));
        callback.applySuccess(object);
    }
}
