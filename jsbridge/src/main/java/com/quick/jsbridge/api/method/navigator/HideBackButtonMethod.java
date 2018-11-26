package com.quick.jsbridge.api.method.navigator;

import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 隐藏导航栏返回按钮,只能在首页使用
 */
public class HideBackButtonMethod implements IApiMethod {

    @Override
    public String name() {
        return "hideBackButton";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        webLoader.getPageControl().getNbBar().hideNbBack();
        callback.applySuccess();
    }
}
