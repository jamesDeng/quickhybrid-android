package com.quick.jsbridge.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quick.core.ui.widget.dialog.QuickDatePickerDialog;
import com.quick.jsbridge.util.common.DateUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import quick.com.jsbridge.R;

/**
 * 弹框接口
 */
public abstract class AbstractDialog {

    /**
     * 确认对话框
     *
     * @param con
     * @param title           标题
     * @param titleGravity    标题对齐方式
     * @param message         提示内容
     * @param messageGravity  内容对齐方式
     * @param cancelable      返回键是否可取消
     * @param button1         按钮1名称
     * @param button2         按钮2名称
     * @param listener1       按钮1触发的事件
     * @param listener2       按钮2触发的事件
     * @param dismissListener 隐藏对话框触发的事件
     */
    public abstract void showConfirmDialog(Context con, String title, int titleGravity, String message, int messageGravity, boolean cancelable, String button1,
                                         String button2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2, DialogInterface.OnDismissListener dismissListener);

    public  void showConfirmDialog(Context con, String title, String message, boolean cancelable, String button1,
                                         String button2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        showConfirmDialog(con, title, message, cancelable, button1, button2, listener1, listener2, null);
    }

    public  void showConfirmDialog(Context con, String title, String message, boolean cancelable, String button1,
                                         String button2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2, DialogInterface.OnDismissListener dismissListener) {
        showConfirmDialog(con, title, 0, message, 0, cancelable, button1, button2, listener1, listener2, dismissListener);
    }

    public  void showConfirmDialog(Context con, String title, String message, boolean cancelable, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2) {
        showConfirmDialog(con, title, message, cancelable, con.getString(R.string.confirm), con.getString(R.string.cancel), listener, listener2);
    }

    public  void showConfirmDialog(Context con, String title, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2) {
        showConfirmDialog(con, title, message, true, listener, listener2);
    }

    public  void showConfirmDialog(Context con, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2) {
        showConfirmDialog(con, message, "", true, listener, listener2);
    }

    public  void showConfirmDialog(Context con, String title, String message, boolean cancelable, DialogInterface.OnClickListener listener) {
        showConfirmDialog(con, title, message, cancelable, con.getString(R.string.confirm), null, listener, null);
    }

    public  void showConfirmDialog(Context con, String title, String message, boolean cancelable, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2, DialogInterface.OnDismissListener dismissListener) {
        showConfirmDialog(con, title, message, cancelable, con.getString(R.string.confirm), null, listener, listener2, dismissListener);
    }

    public  void showConfirmDialog(Context con, String title, String message, DialogInterface.OnClickListener listener) {
        showConfirmDialog(con, title, message, true, listener);
    }

    public  void showConfirmDialog(Context con, String title, String message, String btnText) {
        showConfirmDialog(con, title, message, true, btnText, null, null, null);
    }

    public  void showConfirmDialog(Context con, String title, String message) {
        showConfirmDialog(con, title, message, con.getString(R.string.confirm));
    }


    /**
     * 有图片的确认对话框
     *
     * @param con
     * @param title           标题
     * @param message         提示内容
     * @param cancelable      返回键是否可取消
     * @param btnText         按钮1名称
     * @param listener        按钮2名称
     * @param dismissListener 隐藏对话框触发的事件
     */
    public abstract void showImageDialog(Context con, int image, String title, String message, boolean cancelable, String btnText, DialogInterface.OnClickListener listener, DialogInterface.OnDismissListener dismissListener) ;

    /**
     * 发送对话框
     *
     * @param con
     * @param title
     * @param message
     */
    public abstract void showSendDialog(final Context con, final String title, final String message) ;

    /**
     * 单选对话框
     *
     * @param con        上下文
     * @param title      标题，可为空
     * @param cancelable 是否可取消
     * @param menuitems  选项
     * @param columns    几列
     * @param listener   点击事件
     * @return
     */
    public abstract void showMenuDialog(Context con, String title, boolean cancelable, String menuitems[], int columns,
                                      final DialogInterface.OnClickListener listener);
    /**
     * 选择对话框
     *
     * @param con       上下文
     * @param menuitems 选项
     * @param listener  点击事件
     */
    public void showMenuDialog(Context con, String menuitems[], DialogInterface.OnClickListener listener) {
        showMenuDialog(con, "", true, menuitems, 1, listener);
    }

    /**
     * 选择对话框
     *
     * @param con        上下文
     * @param cancelable 是否可取消
     * @param menuitems  选项
     * @param listener   点击事件
     */
    public void showMenuDialog(Context con, boolean cancelable, String menuitems[], DialogInterface.OnClickListener listener) {
        showMenuDialog(con, "", cancelable, menuitems, 1, listener);
    }

    /**
     * 单选列表对话框
     *
     * @param con        上下文
     * @param title      标题，可为空
     * @param cancelable 是否可取消
     * @param menuitems  选项
     * @param listener   点击事件
     * @return
     */
    public void showMenuDialog(Context con, String title, boolean cancelable, String menuitems[], DialogInterface.OnClickListener listener) {
        showMenuDialog(con, title, cancelable, menuitems, 1, listener);
    }

    /**
     * 多选对话框
     *
     * @param con        上下文
     * @param title      标题
     * @param cancelable 是否可取消
     * @param meumList   选项,key值必须包涵text，isChecked
     * @param listener   点击确认按钮
     */
    public abstract void showMultiMenuDialog(Context con, String title, boolean cancelable, List<HashMap<String, Object>> meumList, DialogInterface.OnClickListener listener);

    /**
     * 日期和时间选择对话框，选选择日期后选择时间
     *
     * @param con      上下文
     * @param title1   日期选择标题
     * @param title2   事件选择标题
     * @param calendar 默认日期
     * @param listener 时间选择事件
     */
    public void pickDateTime(final Context con, String title1, final String title2, final Calendar calendar, final TimePickerDialog.OnTimeSetListener listener) {
        // 增加判断，解决4.X系统可能存在的弹出多个时间选择器的问题
        final boolean[] isShowTime = {false};
        pickDate(con, title1, calendar, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                if (!isShowTime[0]) {
                    isShowTime[0] = true;
                    pickTime(con, title2, calendar, listener);
                }
            }
        });
    }

    /**
     * 创建日期和时间选择对话框
     *
     * @param con      上下文
     * @param title1   选择日期标题
     * @param tltle2   选择时间标题
     * @param views    如果是TextView会自动设置上选择的时间
     * @param calendar 默认时间
     */
    public abstract void pickDateTime(final Context con, final String title1, final String tltle2, final View[] views, final Calendar calendar);

    public void pickDateTime(final Context con, final View[] views, final Calendar calendar) {
        pickDateTime(con, con.getString(R.string.pick_date), con.getString(R.string.pick_time), views, calendar);
    }

    public void pickDateTime(Context con, Calendar calendar, TimePickerDialog.OnTimeSetListener listener) {
        pickDateTime(con, con.getString(R.string.pick_date), con.getString(R.string.pick_time), calendar, listener);
    }

    public void pickDateTime(Context con, View v) {
        pickDateTime(con, v, Calendar.getInstance());
    }

    public void pickDateTime(Context con, View v, Calendar calendar) {
        pickDateTime(con, new View[]{v}, calendar);
    }

    /**
     * 时间选择对话框
     *
     * @param con      上下文
     * @param title    标题
     * @param listener 选择事件
     * @param cal      默认时间
     */
    public abstract void pickTime(Context con, String title, Calendar cal, final TimePickerDialog.OnTimeSetListener listener);

    public void pickTime(Context con, String title, TimePickerDialog.OnTimeSetListener listener) {
        final Calendar cal = Calendar.getInstance();
        pickTime(con, title, cal, listener);
    }

    public void pickTime(Context con, TimePickerDialog.OnTimeSetListener listener) {
        final Calendar cal = Calendar.getInstance();
        pickTime(con, con.getString(R.string.pick_time), cal, listener);
    }

    /**
     * 日期选择对话框
     *
     * @param con      上下文
     * @param title    标题
     * @param calendar 日期
     * @param listener 选择事件
     */
    public void pickDate(Context con, String title, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        pickMonth(con, title, calendar, listener);
    }

    public void pickDate(Context con, String title, DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        pickDate(con, title, calendar, listener);
    }

    public void pickDate(Context con, DatePickerDialog.OnDateSetListener listener) {
        pickDate(con, con.getString(R.string.pick_date), listener);
    }

    public void pickDate(Context con, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        pickDate(con, con.getString(R.string.pick_date), calendar, listener);
    }

    /**
     * 年月选择对话框
     *
     * @param con
     * @param title
     * @param calendar
     * @param listener
     */
    public abstract void pickMonth(Context con, String title, Calendar calendar, final DatePickerDialog.OnDateSetListener listener) ;

    public void pickMonth(Context con, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        pickMonth(con, "", calendar, listener);
    }

    public void pickMonth(Context con, DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        pickMonth(con, calendar, listener);
    }

    /**
     * 自定义对话框
     *
     * @param con
     * @param title
     * @param cancelable
     * @param view
     * @param listener1
     * @param listener2
     */
    public void showCustomeDialog(Context con, String title, boolean cancelable, View view,
                                         DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        showCustomeDialog(con, title, cancelable, view, 0, con.getString(R.string.confirm), con.getString(R.string.cancel), listener1, listener2);
    }

    /**
     * 自定义对话框
     *
     * @param con
     * @param title
     * @param cancelable
     * @param view
     * @param gravity
     * @param listener1
     * @param listener2
     */
    public void showCustomeDialog(Context con, String title, boolean cancelable, View view, int gravity,
                                         DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        showCustomeDialog(con, title, cancelable, view, gravity, con.getString(R.string.confirm), con.getString(R.string.cancel), listener1, listener2);
    }

    /**
     * 自定义对话框
     *
     * @param con
     * @param title
     * @param cancelable
     * @param view
     * @param btn1
     * @param btn2
     * @param listener1
     * @param listener2
     */
    public abstract void showCustomeDialog(Context con, String title, boolean cancelable, View view, int gravity, String btn1, String btn2,
                                         DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2);

    /**
     * 检测EditText内容是否为空，若为空则进行警告提醒
     *
     * @param et
     * @param msg
     * @return
     */
    public boolean showEditTextWarning(EditText et, String msg) {
        if (TextUtils.isEmpty(et.getText())) {
            et.setError(msg);
            et.requestFocus();
            et.setText("");
            return true;
        }
        return false;
    }

    /**
     * 保持对话框显示
     *
     * @param dialog
     */
    public void Keep(DialogInterface dialog) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏对话框
     *
     * @param dialog
     */
    public static void Hide(DialogInterface dialog) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
