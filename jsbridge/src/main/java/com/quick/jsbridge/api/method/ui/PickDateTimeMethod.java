package com.quick.jsbridge.api.method.ui;

import android.app.TimePickerDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TimePicker;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 弹出日期时间选择对话框
 * <p>
 * 参数：
 * title：标题
 * datetime 指定时间 yyyy-MM-dd HH:mm
 * 返回：
 * datetime：格式：yyyy-MM-dd HH:mm
 */
public class PickDateTimeMethod implements IApiMethod {

    @Override
    public String name() {
        return "pickDateTime";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String title1 = param.optString("title1");
        String title2 = param.optString("title2");
        String date = param.optString("datetime");
        final Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            calendar.setTime(DateUtil.convertString2Date(date, "yyyy-MM-dd HH:mm"));
        }
        BaseThemeControl.getInstance().getSelectedTheme().dialog.pickDateTime(webLoader.getPageControl().getActivity(), title1, title2, calendar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String chooseDate = DateUtil.convertDate(calendar.getTime(), "yyyy-MM-dd HH:mm");
                Map<String, Object> map = new HashMap<>();
                map.put("datetime", chooseDate);
                callback.applySuccess(map);
            }
        });
    }
}
