package com.quick.jsbridge.api.method.page;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.quick.core.application.FrmApplication;
import com.quick.jsbridge.bean.QuickBean;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.List;

import quick.com.jsbridge.R;

/**
 * 关闭当前Activity
 * <p>
 * 参数：
 * resultData:    回传上个页面的值,如果为空则不回传
 */
public class PageCloseMethod implements IApiMethod {

    @Override
    public String name() {
        return "close";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {

        int popPageNumber = 1;
        if (param.has("popPageNumber")) {
            popPageNumber = param.optInt("popPageNumber", 1);
        }
        if (popPageNumber > 1) {
            List<Activity> activityList = FrmApplication.getInstance().activityList;
            Activity[] closeActivitys = new Activity[popPageNumber];
            if (activityList != null) {
                for (int i = 0; i < popPageNumber; i++) {
                    if (i >= activityList.size() - 1) {
                        break;
                    }
                    closeActivitys[i] = activityList.get(activityList.size() - i - 1);
                }
            }
            if (closeActivitys.length > 0) {
                for (Activity activity : closeActivitys) {
                    activity.finish();
                }
                return;
            }
        }
        String jsonStr = param.optString(WebloaderControl.RESULT_DATA);
        if (TextUtils.isEmpty(jsonStr)) {
            webLoader.getPageControl().getActivity().finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra(WebloaderControl.RESULT_DATA, jsonStr);
            webLoader.getPageControl().getActivity().setResult(Activity.RESULT_OK, intent);
            webLoader.getPageControl().getActivity().finish();
        }

    }
}
