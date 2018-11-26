package com.quick.jsbridge.api.method.device;

import android.app.Service;
import android.os.Vibrator;
import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 手机震动
 * <p>
 * duration：持续时间
 */
public class DeviceVibrateMethod implements IApiMethod {

    @Override
    public String name() {
        return "vibrate";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        long time = param.optLong("duration", 1000);
        Vibrator vib = (Vibrator) webLoader.getPageControl().getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(time);
    }
}
