package com.quick.jsbridge.api.method.device;

import android.graphics.Point;
import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 打电话
 * <p>
 * 参数：
 * phoneNum：电话号码
 */
public class DeviceCallPhoneMethod implements IApiMethod {

    @Override
    public String name() {
        return "callPhone";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        String phoneNum = param.optString("phoneNum");
        DeviceUtil.callPhone(webLoader.getPageControl().getActivity(), phoneNum);
        callback.applySuccess();
    }
}
