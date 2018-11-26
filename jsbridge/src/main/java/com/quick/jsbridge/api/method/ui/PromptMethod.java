package com.quick.jsbridge.api.method.ui;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IPrompt;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;


/**
 * 弹出输入框
 * <p>
 * 参数：
 * title：标题
 * hint: 输入框提示文字
 * cancelable:是否可以返回取消对话框
 * lines:显示多少行
 * maxLength:最大输入字符数
 * text:默认显示字符串
 * buttonLabels:按钮数组，设置按钮的时候从右往左设置，最多设置2个按钮
 * 返回：
 * which：按钮id
 * content：输入文本
 */
public class PromptMethod implements IApiMethod {

    @Override
    public String name() {
        return "prompt";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        boolean cancelable = !"0".equals(param.optString("cancelable"));
        String title = param.optString("title");
        String hint = param.optString("hint");
        int lines = param.optInt("lines", 1);
        int maxLength = param.optInt("maxLength");
        String text = param.optString("text");

        View view = BaseThemeControl.getInstance().getSelectedTheme().prompt.findView(webLoader.getPageControl().getContext());

        final EditText et = BaseThemeControl.getInstance().getSelectedTheme().prompt.findEditText(view);

        et.setHint(hint);
        et.setText(text);
        et.setSelection(text.length());
        if (lines > 1) {
            et.setLines(lines);
        }
        if (maxLength > 0) {
            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
        JSONArray btnLabels = param.optJSONArray("buttonLabels");
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

        BaseThemeControl.getInstance().getSelectedTheme().dialog.showCustomeDialog(webLoader.getPageControl().getActivity(), title, cancelable, view, Gravity.LEFT, button1, button2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> map = new HashMap<>();
                map.put("which", 1);
                map.put("content", et.getText().toString().trim());
                callback.applySuccess(map);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> object = new HashMap<>();
                object.put("which", 0);
                object.put("content", et.getText().toString().trim());
                callback.applySuccess(object);
            }
        });
    }
}
