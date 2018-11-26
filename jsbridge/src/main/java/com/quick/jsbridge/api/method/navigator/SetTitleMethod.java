package com.quick.jsbridge.api.method.navigator;

import android.view.View;
import android.webkit.WebView;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;
import com.quick.jsbridge.view.ITitleStyle;

import org.json.JSONObject;

import quick.com.jsbridge.R;


/**
 * 设置标题
 * <p>
 * 参数：
 * title:   标题
 * subTitle:副标题
 * clickable：是否可点击，1：是，并且显示小箭头 其他：否
 * direction：箭头方向 top：向上 bottom：向下
 */
public class SetTitleMethod implements IApiMethod {

    @Override
    public String name() {
        return "setTitle";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        String title = param.optString("title");
        String subTitle = param.optString("subTitle");
        boolean clickable = "1".equals(param.optString("clickable", "0"));
        String direction = param.optString("direction", "bottom");
        webLoader.getPageControl().getNbBar().getViewHolder().nbCustomTitleLayout.removeAllViews();
        webLoader.getPageControl().getNbBar().getViewHolder().titleParent.setVisibility(View.VISIBLE);
        webLoader.getPageControl().getNbBar().setNbTitle(title, subTitle);
        if ("bottom".equals(direction)) {
            webLoader.getPageControl().getNbBar().setTitleClickable(clickable, BaseThemeControl.getInstance().getSelectedTheme().titleStyle.img_arrow_black_down());
        } else {
            webLoader.getPageControl().getNbBar().setTitleClickable(clickable, BaseThemeControl.getInstance().getSelectedTheme().titleStyle.img_arrow_black_up());
        }
        if (clickable) {
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnClickNbTitle, callback.getPort());
        } else {
            webLoader.getWebloaderControl().removePort(AutoCallbackDefined.OnClickNbTitle);
        }
    }
}
