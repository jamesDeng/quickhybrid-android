package com.quick.jsbridge.api.method.device;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.application.FrmApplication;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.List;

/**
 * 设置横竖屏
 * <p>
 * 参数：
 * orientation：1表示竖屏，0表示横屏，其他表示跟随系统
 */
public class DeviceSetOrientationMethod implements IApiMethod {

    @Override
    public String name() {
        return "setOrientation";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        int orientation = param.optInt("orientation", ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (orientation >= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED && orientation <= ActivityInfo.SCREEN_ORIENTATION_LOCKED) {
            webLoader.getPageControl().getActivity().setRequestedOrientation(orientation);
            callback.applySuccess();
        } else {
            callback.applyFail("orientation值超出范围，请设置-1到14");
        }
    }
}
