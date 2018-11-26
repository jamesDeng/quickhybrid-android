package com.quick.jsbridge.api.method.device;

import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取当前网络状态
 * <p>
 * 返回：
 * netWorkType：-1:无网络，1：wifi，0：移动网络
 */
public class DeviceGetNetWorkInfoMethod implements IApiMethod {

    @Override
    public String name() {
        return "getNetWorkInfo";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> object = new HashMap<>();
        object.put("netWorkType", DeviceUtil.getNetWorkType(webLoader.getPageControl().getActivity()));
        callback.applySuccess(object);
    }
}
