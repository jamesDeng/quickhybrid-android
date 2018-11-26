package com.quick.jsbridge.api.method.ui;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 消息提示
 * <p>
 * 参数：
 * message： 需要提示的消息内容
 * duration：显示时长，long或short
 */
public class ToastMethod implements IApiMethod {

    @Override
    public String name() {
        return "toast";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String message = param.optString("message");
        String duration = param.optString("duration");
        if ("long".equalsIgnoreCase(duration)) {
            webLoader.getPageControl().toast(message);
        } else {
            webLoader.getPageControl().toast(message);
        }
        callback.applySuccess();
    }
}
