package com.quick.jsbridge.api.method.page;

import android.app.Fragment;
import android.content.Intent;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.quick.jsbridge.bean.QuickBean;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import quick.com.jsbridge.R;


/**
 * 重载页面
 */
public class PageReloadMethod implements IApiMethod {

    @Override
    public String name() {
        return "reload";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        wv.reload();
        callback.applySuccess();
    }
}
