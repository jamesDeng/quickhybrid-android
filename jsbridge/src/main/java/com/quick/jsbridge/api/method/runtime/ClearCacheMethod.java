package com.quick.jsbridge.api.method.runtime;

import android.webkit.WebView;

import com.quick.jsbridge.util.io.FileUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.BuildConfig;

/**
 * 清空webview缓存
 */
public class ClearCacheMethod implements IApiMethod {

    @Override
    public String name() {
        return "clearCache";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        wv.clearHistory();
        wv.clearCache(true);
        wv.clearFormData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtil.deleteFile(new File(webLoader.getPageControl().getContext().getCacheDir().getAbsolutePath()));
                webLoader.getPageControl().getContext().deleteDatabase("webview.db");
                webLoader.getPageControl().getContext().deleteDatabase("webviewCache.db");
                callback.applySuccess();
            }
        }).start();
    }
}
