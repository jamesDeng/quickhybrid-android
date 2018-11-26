package com.quick.jsbridge.api.method.runtime;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 外部浏览器打开网页
 * <p>
 * 参数：
 * url：页面地址
 */
public class OpenUrlMethod implements IApiMethod {

    @Override
    public String name() {
        return "openUrl";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String url = param.optString("url");
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            webLoader.getPageControl().getActivity().startActivity(intent);
        } else {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_data_error));
        }
    }
}
