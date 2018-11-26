package com.quick.jsbridge.api.method.ui;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TimePicker;

import com.quick.core.ui.widget.dialog.ActionSheet;
import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

/**
 * 弹出底部选项按钮
 * <p>
 * 参数：
 * items：多个选项用,隔开
 * cancelable: 是否可取消
 * 返回：
 * which：选中的按钮id
 */
public class ActionSheetMethod implements IApiMethod {

    @Override
    public String name() {
        return "actionSheet";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        boolean cancelable = !"0".equals(param.optString("cancelable"));
        String[] items = null;
        JSONArray itemsJsonObject = param.optJSONArray("items");
        items = JsonUtil.parseJSONArray(itemsJsonObject, items);
        if (items == null) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
            return;
        }

        ActionSheet menuView = new ActionSheet(webLoader.getPageControl().getActivity());
        menuView.setCancelButtonTitle(webLoader.getPageControl().getContext().getString(R.string.cancel));
        menuView.addItems(items);
        menuView.setItemClickListener(new ActionSheet.MenuItemClickListener() {
            @Override
            public void onItemClick(int index) {
                Map<String, Object> object = new HashMap<>();
                object.put("which", index);
                callback.applySuccess(object);
            }
        });
        menuView.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Map<String, Object> object = new HashMap<>();
                object.put("which", -1);
                callback.applySuccess(object);
            }
        });
        menuView.setCancelableOnTouchMenuOutside(cancelable);
        menuView.showMenu();
    }
}
