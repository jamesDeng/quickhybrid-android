package com.quick.jsbridge.api.method.util;

import android.app.Fragment;
import android.webkit.WebView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.jsbridge.util.io.FileUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.io.File;

import quick.com.jsbridge.R;

/**
 * 打开文件
 * <p>
 * 参数：
 * path:文件本地路径
 */
public class OpenFileMethod implements IApiMethod {

    @Override
    public String name() {
        return "openFile";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String path = param.optString("path");
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
        } else {
            FileUtil.openFile(webLoader.getPageControl().getActivity(), file);
            callback.applySuccess();
        }
    }
}
