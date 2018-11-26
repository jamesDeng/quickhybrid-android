package com.quick.jsbridge.api;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.net.HttpUtil;
import com.quick.jsbridge.util.app.AppUtil;
import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.util.gis.CoordMath;
import com.quick.jsbridge.util.gis.MyLatLngPoint;
import com.quick.jsbridge.util.io.FileUtil;
import com.quick.jsbridge.api.method.runtime.ClearCacheMethod;
import com.quick.jsbridge.api.method.runtime.ClipboardMethod;
import com.quick.jsbridge.api.method.runtime.GetAppVersionMethod;
import com.quick.jsbridge.api.method.runtime.GetGeolocationMethod;
import com.quick.jsbridge.api.method.runtime.GetQuickVersionMethod;
import com.quick.jsbridge.api.method.runtime.LaunchAppMethod;
import com.quick.jsbridge.api.method.runtime.OpenUrlMethod;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quick.com.jsbridge.BuildConfig;
import quick.com.jsbridge.R;


/**
 * Created by dailichun on 2017/12/7.
 * 运行时API
 */
public class RuntimeApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "runtime";

    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new ClearCacheMethod(),
                new ClipboardMethod(),
                new GetAppVersionMethod(),
                new GetGeolocationMethod(),
                new GetQuickVersionMethod(),
                new LaunchAppMethod(),
                new OpenUrlMethod()
        );
    }
}
