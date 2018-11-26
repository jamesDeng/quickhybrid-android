package com.quick.jsbridge.api.method.device;

import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 控制键盘显隐，如果输入法在窗口上已经显示，则隐藏，反之则显示
 */
public class DeviceCloseInputKeyboardMethod implements IApiMethod {

    @Override
    public String name() {
        return "closeInputKeyboard";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        wv.postDelayed(new Runnable() {
            public void run() {
                DeviceUtil.hideInputKeyboard(webLoader.getPageControl().getActivity());
                callback.applySuccess();
            }
        }, 200);
    }
}
