package com.quick.jsbridge.api.method.runtime;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 打开第三方app
 * <p>
 * 参数：
 * packageName ：applicationId
 * className   ：指定打开的页面类名，可为空
 * actionName  ：manifest中activity设置的actionname
 * scheme      ：manifest中activity设置的scheme
 * data        ：传给页面的参数
 */
public class LaunchAppMethod implements IApiMethod {

    @Override
    public String name() {
        return "launchApp";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        String packageName = param.optString("packageName");
        String className = param.optString("className");
        String actionName = param.optString("actionName");
        String scheme = param.optString("scheme");
        String data = param.optString("data");
        try {
            Intent intent = null;
            if (!TextUtils.isEmpty(packageName)) {
                intent = RuntimeUtil.getLaunchAppIntent(webLoader.getPageControl().getActivity(), packageName, className);

            } else if (!TextUtils.isEmpty(actionName)) {
                intent = new Intent(actionName);

            } else if (!TextUtils.isEmpty(scheme)) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme + "://"));
            }

            if (intent != null) {
                if (!TextUtils.isEmpty(data)) {
                    intent.putExtra("data", data);
                }
                webLoader.getPageControl().getActivity().startActivity(intent);
                callback.applySuccess();
            } else {
                callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.applyFail(e.getMessage());
        }
    }
}
