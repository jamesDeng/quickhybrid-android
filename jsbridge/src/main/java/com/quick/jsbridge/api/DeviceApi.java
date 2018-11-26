package com.quick.jsbridge.api;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.api.method.device.DeviceCallPhoneMethod;
import com.quick.jsbridge.api.method.device.DeviceCloseInputKeyboardMethod;
import com.quick.jsbridge.api.method.device.DeviceGetDeviceIdMethod;
import com.quick.jsbridge.api.method.device.DeviceGetNetWorkInfoMethod;
import com.quick.jsbridge.api.method.device.DeviceGetVendorInfoMethod;
import com.quick.jsbridge.api.method.device.DeviceSendMsgMethod;
import com.quick.jsbridge.api.method.device.DeviceSetOrientationMethod;
import com.quick.jsbridge.api.method.device.DeviceVibrateMethod;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dailichun on 2017/12/7.
 * 设备相关API
 */
public class DeviceApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "device";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new DeviceCallPhoneMethod(),
                new DeviceCloseInputKeyboardMethod(),
                new DeviceGetDeviceIdMethod(),
                new DeviceGetNetWorkInfoMethod(),
                new DeviceGetVendorInfoMethod(),
                new DeviceSendMsgMethod(),
                new DeviceSetOrientationMethod(),
                new DeviceVibrateMethod()
        );
    }
}
