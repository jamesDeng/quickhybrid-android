package com.quick.jsbridge.api.method.device;

import android.content.pm.ActivityInfo;
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
 * 获取设备基础信息
 * <p>
 * 返回：
 * UAInfo
 * pixel
 * deviceId
 * netWorkType
 */
public class DeviceGetVendorInfoMethod implements IApiMethod {

    @Override
    public String name() {
        return "getVendorInfo";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        Map<String, Object> object = new HashMap<>();
        //设备厂商以及型号
        String type = android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL;
        object.put("uaInfo", "android " + type);
        //设备分辨率
        Point point = DeviceUtil.getPhonePixel(webLoader.getPageControl().getActivity());
        object.put("pixel", point.x + "*" + point.y);
        //唯一标识(机器码或者MAC地址)
        object.put("deviceId", DeviceUtil.getDeviceId(webLoader.getPageControl().getActivity()));
        //网络状态 -1:无网络1：wifi 0：移动网络
        object.put("netWorkType", DeviceUtil.getNetWorkType(webLoader.getPageControl().getActivity()));
        callback.applySuccess(object);
    }
}
