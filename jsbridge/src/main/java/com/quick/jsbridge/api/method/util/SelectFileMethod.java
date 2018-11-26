package com.quick.jsbridge.api.method.util;

import android.app.Fragment;
import android.webkit.WebView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.api.PageApi;
import com.quick.jsbridge.api.method.page.PageOpenLocalMethod;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 打开二维码
 * <p>
 * 参数：
 * videoUrl：视频地址
 */
public class SelectFileMethod implements IApiMethod {


    private PageOpenLocalMethod pageOpenLocalMethod;

    public SelectFileMethod(PageOpenLocalMethod pageOpenLocalMethod){
        this.pageOpenLocalMethod  = pageOpenLocalMethod;
    }

    @Override
    public String name() {
        return "selectFile";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        try {
            param.putOpt("className", BaseThemeControl.getInstance().getSelectedTheme().selectFileCaptureActivity.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pageOpenLocalMethod.execute(webLoader, wv, param, callback);
    }
}
