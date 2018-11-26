package com.quick.jsbridge.api.method.auth;

import android.webkit.WebView;

import com.quick.jsbridge.util.jsbridge.QuickModulesUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.bridge.JSBridge;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * H5页面初始化配置
 * 先验证白名单，验证失败直接显示错误状态页面，验证通过后注册自定义API，注册成功进行成功回调，否则进行失败回调。
 * <p>
 * 参数：
 * jsApiList：自定义API数组[{'ui':'com.quick.jsbridge.UiApi'},{'util':'com.quick.jsbridge.UtilApi'}]
 */
public class AuthConfigMethod implements IApiMethod {

    @Override
    public String name() {
        return "config";
    }

    @Override
    public void execute(final IQuickFragment webLoader, final WebView wv, final JSONObject param,final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //白名单验证
                webLoader.getWebloaderControl().setHasConfig(true);
                //注册自定义API
                boolean falg = true;
                try {
                    JSONArray apiJsonArray = param.optJSONArray("jsApiList");
                    if (apiJsonArray != null) {
                        for (int i = 0; i < apiJsonArray.length(); i++) {
                            String apiName = apiJsonArray.optString(i);
                            String apiPath = QuickModulesUtil.getProperties(wv.getContext(), apiName);

                            if (apiPath != null && apiPath != "") {
                                try {
                                    Class c = Class.forName(apiPath);

                                    JSBridge.getInstance().register((IBridgeImpl)c.newInstance());
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    callback.applyFail(e.toString());
                                    falg = false;
                                    break;
                                }
                            }

                            if (!falg) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    falg = false;
                    callback.applyFail(e.toString());
                }
                if (falg) {
                    callback.applySuccess();
                }
            }
        }).start();
    }
}
