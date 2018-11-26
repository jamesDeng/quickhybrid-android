package com.quick.jsbridge.api.method.device;

import android.webkit.WebView;

import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 发短信
 * <p>
 * 参数：
 * phoneNum：电话号码
 * message:短信内容
 */
public class DeviceSendMsgMethod implements IApiMethod {

    @Override
    public String name() {
        return "sendMsg";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        String phoneNum = param.optString("phoneNum");
        String message = param.optString("message");
        DeviceUtil.sendMsg(webLoader.getPageControl().getActivity(), phoneNum, message);
        callback.applySuccess();
    }
}
