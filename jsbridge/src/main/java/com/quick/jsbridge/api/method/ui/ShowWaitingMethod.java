package com.quick.jsbridge.api.method.ui;

import android.app.TimePickerDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TimePicker;

import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 显示loading图标
 */
public class ShowWaitingMethod implements IApiMethod {

    @Override
    public String name() {
        return "showWaiting";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String message = param.optString("message");
        webLoader.getPageControl().showLoading(message);
        callback.applySuccess();
    }
}
