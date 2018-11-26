package com.quick.jsbridge.api.method.util;

import android.app.Fragment;
import android.content.Intent;
import android.webkit.WebView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import me.iwf.photopicker.PhotoPicker;
import quick.com.jsbridge.R;

/**
 * 多图片选择(配合上传文件API同时使用)
 * <p>
 * 参数：
 * photoCount:可选图片的最大数,默认为9
 * showCamera:是否允许拍照，1：允许；0：不允许，默认不允许
 * showGif：是否显示gif图片，1：显示；0：不显示，默认不显示
 * previewEnabled：是否允许预览，1：允许，0：不允许，默认允许
 * selectedPhotos:已选图片,json数组
 */
public class SelectImageMethod implements IApiMethod {

    @Override
    public String name() {
        return "selectImage";
    }

    @Override
    public void execute(final IQuickFragment webLoader,final WebView wv,final JSONObject param,final Callback callback) {
        int photoCount = param.optInt("photoCount", 9);
        boolean showCamera = "1".equals(param.optString("showCamera", "0"));
        boolean showGif = "1".equals(param.optString("showGif", "0"));
        boolean previewEnabled = "1".equals(param.optString("previewEnabled", "1"));
        String[] items = new String[]{};
        JSONArray itemsJsonObject = param.optJSONArray("selectedPhotos");
        items = JsonUtil.parseJSONArray(itemsJsonObject, items);
        ArrayList<String> selectedPhotos = new ArrayList<>(Arrays.asList(items));
        if (photoCount < 1) {
            callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.status_request_error));
        } else {
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnChoosePic, callback.getPort());
            PhotoPicker.PhotoPickerBuilder builder = PhotoPicker.builder()
                    //设置最多选择数量
                    .setPhotoCount(photoCount)
                    //是否允许拍照
                    .setShowCamera(showCamera)
                    //是否显示gif图片
                    .setShowGif(showGif)
                    //设置已选图片
                    .setSelected(selectedPhotos)
                    //设置是否允许预览
                    .setPreviewEnabled(previewEnabled);
            Intent intent = builder.getIntent(webLoader.getPageControl().getActivity());
            Object fragment = webLoader.getPageControl().getFragment();
            if (fragment instanceof Fragment) {
                ((Fragment) fragment).startActivityForResult(intent, PhotoPicker.REQUEST_CODE);
            } else if (fragment instanceof android.support.v4.app.Fragment) {
                ((android.support.v4.app.Fragment) fragment).startActivityForResult(intent, PhotoPicker.REQUEST_CODE);
            }
        }
    }
}
