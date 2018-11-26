package com.quick.jsbridge.api.method.device;

import android.content.pm.ActivityInfo;
import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取设备唯一编码
 * <p>
 * 返回：
 * deviceId
 */
public class DeviceGetDeviceIdMethod implements IApiMethod {

    @Override
    public String name() {
        return "getDeviceId";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> object = new HashMap<>();
        object.put("deviceId", DeviceUtil.getDeviceId(webLoader.getPageControl().getActivity()));
        callback.applySuccess(object);
    }
}
