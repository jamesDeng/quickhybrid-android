package com.quick.jsbridge.api.method.util;

import android.app.Fragment;
import android.webkit.WebView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 打开二维码
 * <p>
 * 参数：
 * videoUrl：视频地址
 */
public class ScanMethod implements IApiMethod {

    @Override
    public String name() {
        return "scan";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        Object fragment = webLoader.getPageControl().getFragment();
        IntentIntegrator integrator = null;
        if (fragment instanceof Fragment) {
            integrator = IntentIntegrator.forFragment((Fragment) fragment);
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            integrator = IntentIntegrator.forSupportFragment((android.support.v4.app.Fragment) fragment);
        }
        if (integrator != null) {
            integrator.setCaptureActivity(BaseThemeControl.getInstance().getSelectedTheme().sacnCaptureActivity);
            integrator.initiateScan();
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnScanCode, callback.getPort());
        }
    }
}
