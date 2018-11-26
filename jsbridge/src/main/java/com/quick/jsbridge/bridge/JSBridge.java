package com.quick.jsbridge.bridge;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.ui.widget.segbar.ICustomTitleViewFactory;
import com.quick.jsbridge.api.AuthApi;
import com.quick.jsbridge.api.DeviceApi;
import com.quick.jsbridge.api.NavigatorApi;
import com.quick.jsbridge.api.PageApi;
import com.quick.jsbridge.api.RuntimeApi;
import com.quick.jsbridge.api.UIApi;
import com.quick.jsbridge.api.UtilApi;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IPrompt;
import com.quick.jsbridge.view.IQuickFragment;
import com.quick.jsbridge.view.ITitleStyle;
import com.quick.jsbridge.view.webview.QuickWebView;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dailichun on 2017/12/6.
 * 定义交互通道
 */

public class JSBridge {

    /**
     * Quick约定的schem值,不可更改
     */
    private static final String QUICK_SCHEME = "QuickHybridJSBridge";

    /**
     * 注册方法缓存对象
     */
    private  Map<String, Map<String, IApiMethod>> exposedMethods = new HashMap<>();

    private static JSBridge instance = new JSBridge();

    static {
        instance.register(new AuthApi());
        instance.register(new DeviceApi());
        instance.register(new RuntimeApi());
        instance.register(new PageApi());
        instance.register(new NavigatorApi());
        instance.register(new UIApi());
        instance.register(new UtilApi());
    }

    private JSBridge(){
    }

    public static JSBridge getInstance(){
        return instance;
    }

    /**
     * 将api注册到缓存中
     *
     * @param api  模块中定义的api
     */
    public void register(IBridgeImpl api) {
        try {

            Map<String,IApiMethod> methodMap = new HashMap<>();

            for (IApiMethod m:api.methods()) {
                methodMap.put(m.name(),m);
            }

            exposedMethods.put(api.getRegisterName(), methodMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否注册到缓存中
     *
     * @param apiModelName API别名
     */
    public boolean isRegister(String apiModelName) {
        return exposedMethods.containsKey(apiModelName);
    }

    /**
     * 通过反射调用API
     *
     * @param webLoader
     * @param url
     * @param hasConfig
     * @return
     */
    public String callJava(IQuickFragment webLoader, String url, boolean hasConfig) {
        if (webLoader == null) {
            return "QuickFragment is null";
        }

        Callback callback = null;
        String error = null;
        String methodName = null;
        String apiName = null;
        String param = null;
        String port;
        QuickWebView webView = webLoader.getQuickWebView();

        boolean parseSuccess = false;
        while (!parseSuccess) {
            if (url.contains("#")) {
                error = "url不能包涵特殊字符'#'";
                break;
            }
            if (!url.startsWith(QUICK_SCHEME)) {
                error = "scheme错误";
                break;
            }
            if (TextUtils.isEmpty(url)) {
                error = "url不能为空";
                break;
            }
            Uri uri = Uri.parse(url);
            if (uri == null) {
                error = "url解析失败";
                break;
            }
            apiName = uri.getHost();
            if (TextUtils.isEmpty(apiName)) {
                error = "API_Nam为空";
                break;
            }
            port = uri.getPort() + "";
            if (TextUtils.isEmpty(port)) {
                error = "port为空";
                break;
            }
            callback = new Callback(port, webView);
            methodName = uri.getPath();
            methodName = methodName.replace("/", "");
            if (TextUtils.isEmpty(methodName)) {
                error = "方法名为空";
                break;
            }
            param = uri.getQuery();
            if (TextUtils.isEmpty(param)) {
                param = "{}";
            }
            parseSuccess = true;
        }

        //参数解析失败
        if (!parseSuccess) {
            if (callback == null) {
                new Callback(Callback.ERROR_PORT, webView).applyNativeError(url,error);
            } else {
                callback.applyFail(error);
            }
            return error;
        }
        //解析成功 调用本地api
        if (exposedMethods.containsKey(apiName)) {
            if (chechConfig(callback, hasConfig, apiName, methodName)) {
                Map<String, IApiMethod> methodHashMap = exposedMethods.get(apiName);
                if (methodHashMap != null && methodHashMap.size() != 0 && methodHashMap.containsKey(methodName)) {
                    IApiMethod method = methodHashMap.get(methodName);
                    if (method != null) {
                        try {
                            method.execute(webLoader, webView, new JSONObject(param), callback);
//                          LogUtil.logBuried(apiName + "." + methodName);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.applyFail(e.toString());
                        }
                    }
                } else {
                    //未找到该方法
                    error = apiName + "." + methodName + "未找到";
                    callback.applyFail(error);
                    return error;
                }
            }
        } else {
            //未注册API
            error = apiName + "未注册";
            callback.applyFail(error);
            return error;
        }
        return null;
    }

    /**
     * 验证config
     *
     * @param callback
     * @param hasConfig
     * @param apiName
     * @return
     */
    private boolean chechConfig(Callback callback, boolean hasConfig, String apiName, String methodName) {
        if (hasConfig) {
            return true;
        } else {
            if (apiName.equals(UIApi.RegisterName) || apiName.equals(PageApi.RegisterName)
                    || apiName.equals(NavigatorApi.RegisterName)
                    || methodName.contains("getQuickVersion")
                    || methodName.contains("getAppVersion")
                    || methodName.contains("config")) {
                return true;
            } else {
                callback.applyFail("没有权限");
                //未通过配置
                return false;
            }
        }
    }
}
