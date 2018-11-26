package com.quick.jsbridge.api.method.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.DatePicker;
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
 * 弹出时间选择对话框
 * <p>
 * 参数：
 * title：标题
 * datetime 指定时间 yyyy-MM-dd HH:mm或者HH:mm
 * 返回：
 * time：格式：HH:mm
 */
public class PickTimeMethod implements IApiMethod {

    @Override
    public String name() {
        return "pickTime";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        String title = param.optString("title");
        String date = param.optString("datetime");
        final Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            if (date.contains(" ")) {
                calendar.setTime(DateUtil.convertString2Date(date, "yyyy-MM-dd HH:mm"));
            } else {
                calendar.setTime(DateUtil.convertString2Date(date, "HH:mm"));
            }
        }
        BaseThemeControl.getInstance().getSelectedTheme().dialog.pickTime(webLoader.getPageControl().getActivity(), title, calendar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String chooseTime = DateUtil.convertDate(calendar.getTime(), "HH:mm");
                Map<String, Object> map = new HashMap<>();
                map.put("time", chooseTime);
                callback.applySuccess(map);
            }
        });
    }
}
