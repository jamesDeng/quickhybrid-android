package com.quick.jsbridge.api;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.quick.core.ui.widget.dialog.ActionSheet;
import com.quick.jsbridge.util.common.DateUtil;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.api.method.ui.ActionSheetMethod;
import com.quick.jsbridge.api.method.ui.CloseWaitingMethod;
import com.quick.jsbridge.api.method.ui.ConfirmMethod;
import com.quick.jsbridge.api.method.ui.PickDateMethod;
import com.quick.jsbridge.api.method.ui.PickDateTimeMethod;
import com.quick.jsbridge.api.method.ui.PickMonthMethod;
import com.quick.jsbridge.api.method.ui.PickTimeMethod;
import com.quick.jsbridge.api.method.ui.PopWindowMethod;
import com.quick.jsbridge.api.method.ui.PromptMethod;
import com.quick.jsbridge.api.method.ui.ShowWaitingMethod;
import com.quick.jsbridge.api.method.ui.ToastMethod;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IPrompt;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quick.com.jsbridge.R;

import static android.graphics.Color.parseColor;


/**
 * Created by dailichun on 2017/12/6.
 */
public class UIApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "ui";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new ActionSheetMethod(),
                new CloseWaitingMethod(),
                new ConfirmMethod(),
                new PickDateMethod(),
                new PickDateTimeMethod(),
                new PickMonthMethod(),
                new PickTimeMethod(),
                new PopWindowMethod(),
                new PromptMethod(),
                new ShowWaitingMethod(),
                new ToastMethod()
        );
    }
}
