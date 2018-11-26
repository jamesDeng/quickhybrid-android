package com.quick.jsbridge.api.method.page;

import android.app.Fragment;
import android.content.Intent;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.quick.jsbridge.util.common.QuickUtil;
import com.quick.jsbridge.bean.QuickBean;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 打开原生页面
 * <p>
 * 参数：
 * className: 原生页面activity类名
 * isOpenExist：是否打开已存在的页面,为1时:如果页面已经存在，则直接打开且关闭页面上层所有的其他页面；如果不存在，则打开一个新页面
 * data:        传递下一页面的数据,json格式
 */
public class PageOpenLocalMethod implements IApiMethod {

    @Override
    public String name() {
        return "openLocal";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {


        String activityName = param.optString("className");
        String isOpenExist = param.optString("isOpenExist");
        String data = param.optString("data");
        try {
            Class clz = Class.forName(activityName);
            Intent intent = new Intent(webLoader.getPageControl().getContext(), clz);
            intent.putExtra("from", "quick");
            if ("1".equals(isOpenExist)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            if (data.startsWith("[")) {
                QuickUtil.putIntentExtra(new JSONArray(data), intent);
            } else if (data.startsWith("{")) {
                QuickUtil.putIntentExtra(new JSONObject(data), intent);
            }
            Object fragment = webLoader.getPageControl().getFragment();
            if (fragment instanceof Fragment) {
                ((Fragment) fragment).startActivityForResult(intent, WebloaderControl.INTENT_REQUEST_CODE);
            } else if (fragment instanceof android.support.v4.app.Fragment) {
                ((android.support.v4.app.Fragment) fragment).startActivityForResult(intent, WebloaderControl.INTENT_REQUEST_CODE);
            }
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnPageResult, callback.getPort());
        } catch (Exception e) {
            e.printStackTrace();
            callback.applyFail(e.toString());
        }
    }
}
