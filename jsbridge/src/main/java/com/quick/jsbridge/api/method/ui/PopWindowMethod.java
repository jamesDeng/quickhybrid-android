package com.quick.jsbridge.api.method.ui;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.DatePicker;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

import static android.graphics.Color.parseColor;

/**
 * 弹出顶部选项按钮
 * <p>
 * 参数：
 * iconFilterColor：图标过滤色
 * titleItems：多个选项用,隔开
 * iconItems: 图标 多个,隔开
 * 返回：
 * which：点击按钮id
 */
public class PopWindowMethod implements IApiMethod {

    @Override
    public String name() {
        return "popWindow";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String iconFilterColor = param.optString("iconFilterColor");
        JSONArray titleJsonObject = param.optJSONArray("titleItems");
        JSONArray iconJsonObject = param.optJSONArray("iconItems");
        if (titleJsonObject == null) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
            return;
        }
        String[] titleItems = null;
        String[] iconItems = null;
        titleItems = JsonUtil.parseJSONArray(titleJsonObject, titleItems);
        iconItems = JsonUtil.parseJSONArray(iconJsonObject, iconItems);
        if (iconItems != null && titleItems.length != iconItems.length) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
            return;
        }

        int iconColor = 0;
        if (!TextUtils.isEmpty(iconFilterColor)) {
            iconColor = parseColor("#" + iconFilterColor);
        }
        BaseThemeControl.getInstance().getSelectedTheme().frmPopMenu.show(webLoader,titleItems,iconItems,iconColor,callback);
    }
}
