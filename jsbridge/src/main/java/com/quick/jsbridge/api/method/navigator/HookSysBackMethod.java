package com.quick.jsbridge.api.method.navigator;

import android.webkit.WebView;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;


/**
 * 监听系统返回按钮
 */
public class HookSysBackMethod implements IApiMethod {

    @Override
    public String name() {
        return "hookSysBack";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnClickBack, callback.getPort());
    }
}
