package com.quick.jsbridge.api.method.navigator;

import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 显示导航栏
 */
public class ShowMethod implements IApiMethod {

    @Override
    public String name() {
        return "show";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        webLoader.getPageControl().getNbBar().show();
        callback.applySuccess();
    }
}
