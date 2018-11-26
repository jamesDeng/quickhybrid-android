package com.quick.jsbridge.api.method.runtime;

import android.webkit.WebView;

import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.util.io.FileUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.io.File;

/**
 * 复制到剪贴板
 * <p>
 * 参数：
 * text：复制信息
 */
public class ClipboardMethod implements IApiMethod {

    @Override
    public String name() {
        return "clipboard";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String text = param.optString("text");
        RuntimeUtil.clipboard(webLoader.getPageControl().getContext(), text);
        callback.applySuccess();
    }
}
