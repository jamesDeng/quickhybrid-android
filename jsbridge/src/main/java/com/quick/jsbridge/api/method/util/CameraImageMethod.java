package com.quick.jsbridge.api.method.util;

import android.app.Fragment;
import android.webkit.WebView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.jsbridge.util.device.PhotoSelector;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.control.WebloaderControl;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 打开摄像机拍照
 * <p>
 * 参数：
 * selectedPhotos:图片地址，支持网络图片，手机本地图片
 * index：默认显示图片序号
 * showDeleteButton:是否显示删除按钮，1：显示，0：不显示，默认不显示。如果显示按钮则自动注册回调事件。
 */
public class CameraImageMethod implements IApiMethod {

    @Override
    public String name() {
        return "cameraImage";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        int width = param.optInt("width", 720);
        int quality = param.optInt("quality", 70);
        webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnChoosePic, callback.getPort());
        PhotoSelector photoSelector = webLoader.getWebloaderControl().getPhotoSelect();
        photoSelector.setdQuality(quality);
        photoSelector.setWidth(width);
        photoSelector.requestSysCamera(webLoader.getPageControl().getContext()
                ,webLoader, WebloaderControl.CAMERA_REQUEST_CODE);
    }
}
