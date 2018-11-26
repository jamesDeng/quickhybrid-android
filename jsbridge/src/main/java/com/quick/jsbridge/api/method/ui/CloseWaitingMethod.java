package com.quick.jsbridge.api.method.ui;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

/**
 * 隐藏loading图标
 */
public class CloseWaitingMethod implements IApiMethod {

    @Override
    public String name() {
        return "closeWaiting";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        wv.post(new Runnable() {
            public void run() {
                webLoader.getPageControl().hideLoading();
                callback.applySuccess();
            }
        });
    }
}
