package com.quick.jsbridge.util.common;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.quick.jsbridge.view.AbstractDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * 默认 dialog
 */
public class DefaultDialog extends AbstractDialog {
    @Override
    public void showConfirmDialog(Context con, String title, int titleGravity, String message, int messageGravity, boolean cancelable, String button1, String button2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2, DialogInterface.OnDismissListener dismissListener) {
        DialogUtil.showConfirmDialog(con,title,titleGravity,message,messageGravity,cancelable,button1,button2,listener1,listener2,dismissListener);
    }

    @Override
    public void showImageDialog(Context con, int image, String title, String message, boolean cancelable, String btnText, DialogInterface.OnClickListener listener, DialogInterface.OnDismissListener dismissListener) {
        DialogUtil.showImageDialog(con,image,title,message,cancelable,btnText,listener,dismissListener);
    }

    @Override
    public void showSendDialog(Context con, String title, String message) {
        DialogUtil.showSendDialog(con,title,message);
    }

    @Override
    public void showMenuDialog(Context con, String title, boolean cancelable, String[] menuitems, int columns, DialogInterface.OnClickListener listener) {
        DialogUtil.showMenuDialog(con,title,cancelable,menuitems,columns,listener);
    }

    @Override
    public void showMultiMenuDialog(Context con, String title, boolean cancelable, List<HashMap<String, Object>> meumList, DialogInterface.OnClickListener listener) {
        DialogUtil.showMultiMenuDialog(con,title,cancelable,meumList,listener);
    }

    @Override
    public void pickDateTime(Context con, String title1, String tltle2, View[] views, Calendar calendar) {
        DialogUtil.pickDateTime(con,title1,tltle2,views,calendar);
    }

    @Override
    public void pickTime(Context con, String title, Calendar cal, TimePickerDialog.OnTimeSetListener listener) {
        DialogUtil.pickTime(con,title,cal,listener);
    }

    @Override
    public void pickMonth(Context con, String title, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        DialogUtil.pickMonth(con,title,calendar,listener);
    }

    @Override
    public void showCustomeDialog(Context con, String title, boolean cancelable, View view, int gravity, String btn1, String btn2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        DialogUtil.showCustomeDialog(con,title,cancelable,view,gravity,btn1,btn2,listener1,listener2);
    }
}
