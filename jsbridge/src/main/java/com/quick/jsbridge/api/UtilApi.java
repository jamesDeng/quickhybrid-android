package com.quick.jsbridge.api;

import android.app.Fragment;
import android.content.Intent;
import android.webkit.WebView;


import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.util.device.PhotoSelector;
import com.quick.jsbridge.util.io.FileUtil;
import com.quick.jsbridge.api.method.page.PageOpenLocalMethod;
import com.quick.jsbridge.api.method.util.CameraImageMethod;
import com.quick.jsbridge.api.method.util.OpenFileMethod;
import com.quick.jsbridge.api.method.util.ScanMethod;
import com.quick.jsbridge.api.method.util.SelectFileMethod;
import com.quick.jsbridge.api.method.util.SelectImageMethod;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import quick.com.jsbridge.R;

/**
 * Created by dailichun on 2017/12/6.
 */
public class UtilApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "util";

    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {

        PageOpenLocalMethod openLocalMethod = new PageOpenLocalMethod();
        return Arrays.asList(
                new CameraImageMethod(),
                new OpenFileMethod(),
                new ScanMethod(),
                new SelectFileMethod(openLocalMethod),
                new SelectImageMethod()
        );
    }
}
