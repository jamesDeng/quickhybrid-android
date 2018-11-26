package com.quick.jsbridge.api.method.ui;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

/**
 * 弹出确认对话框
 * <p>
 * 参数：
 * title：标题
 * message：消息
 * cancelable：是否可取消
 * buttonLabels：按钮数组，设置按钮的时候从右往左设置，最多设置2个按钮
 * 返回：
 * which：按钮id
 */
public class ConfirmMethod implements IApiMethod {

    @Override
    public String name() {
        return "confirm";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        final String title = param.optString("title");
        final String msg = param.optString("message");
        final boolean cancelable = !"0".equals(param.optString("cancelable"));
        final JSONArray btnLabels = param.optJSONArray("buttonLabels");
        if (TextUtils.isEmpty(msg)) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
        } else {
            String[] btnItems = null;
            btnItems = JsonUtil.parseJSONArray(btnLabels, btnItems);
            String button1 = "";
            String button2 = "";
            if (btnItems != null) {
                if (btnItems.length == 1) {
                    button1 = btnItems[0];
                } else if (btnItems.length > 1) {
                    button2 = btnItems[0];
                    button1 = btnItems[1];
                }
            }
            BaseThemeControl.getInstance().getSelectedTheme().dialog.showConfirmDialog(webLoader.getPageControl().getActivity(), title, msg, cancelable, button1, button2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("which", 1);
                    callback.applySuccess(map);
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("which", 0);
                    callback.applySuccess(map);
                }
            });
        }
    }
}
