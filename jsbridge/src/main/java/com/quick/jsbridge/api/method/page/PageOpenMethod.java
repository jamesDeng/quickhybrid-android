package com.quick.jsbridge.api.method.page;

import android.app.Fragment;
import android.content.Intent;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.bean.QuickBean;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 打开新的H5页面
 * <p>
 * 参数：
 * 其他参数见{@link QuickBean}属性
 */

public class PageOpenMethod implements IApiMethod {

    public static final String SCREEN_ORIENTATION = "orientation";

    @Override
    public String name() {
        return "open";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {

        QuickBean bean = new Gson().fromJson(String.valueOf(param), QuickBean.class);
        Intent intent = new Intent();

        intent.setClass(webLoader.getPageControl().getContext(), BaseThemeControl.getInstance().getSelectedTheme().quickWebLoader);
        if (bean == null) {
            callback.applyFail("请求参数错误");
        } else {
            intent.putExtra("bean", bean);
            intent.putExtra(SCREEN_ORIENTATION, bean.orientation);
            //注册长期回调，如果下一个页面回传数据会主动回调
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnPageResult, callback.getPort());
            Object fragment = webLoader.getPageControl().getFragment();
            if (fragment instanceof Fragment) {
                ((Fragment) fragment).startActivityForResult(intent, WebloaderControl.INTENT_REQUEST_CODE);
            } else if (fragment instanceof android.support.v4.app.Fragment) {
                ((android.support.v4.app.Fragment) fragment).startActivityForResult(intent, WebloaderControl.INTENT_REQUEST_CODE);
            }
        }
    }
}
