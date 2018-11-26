package com.quick.jsbridge.bridge;

import android.webkit.WebView;

import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * API 方法
 */
public interface IApiMethod {

    /**
     * 方法名称
     * @return
     */
    String name();

    /**
     * 方法执行
     * @param webLoader
     * @param wv
     * @param param
     * @param callback
     */
    void execute(IQuickFragment webLoader, WebView wv, JSONObject param, final Callback callback);
}
