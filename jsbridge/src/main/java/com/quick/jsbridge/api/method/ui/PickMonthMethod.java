package com.quick.jsbridge.api.method.ui;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.DatePicker;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 弹出年月选择对话框
 * <p>
 * 参数：
 * title： 标题
 * datetime： 指定日期 yyyy-MM
 * 返回：
 * month： 格式：yyyy-MM
 */
public class PickMonthMethod implements IApiMethod {

    @Override
    public String name() {
        return "pickMonth";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String title = param.optString("title");
        String date = param.optString("datetime");
        final Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            calendar.setTime(DateUtil.convertString2Date(date, "yyyy-MM"));
        }
        BaseThemeControl.getInstance().getSelectedTheme().dialog.pickMonth(webLoader.getPageControl().getActivity(), title, calendar, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String chooseDate = DateUtil.convertDate(calendar.getTime(), "yyyy-MM");
                Map<String, Object> map = new HashMap<>();
                map.put("month", chooseDate);
                callback.applySuccess(map);
            }
        });
    }
}
