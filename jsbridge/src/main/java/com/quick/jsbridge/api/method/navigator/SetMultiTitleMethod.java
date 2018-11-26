package com.quick.jsbridge.api.method.navigator;

import android.view.View;
import android.webkit.WebView;

import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.core.ui.widget.segbar.ICustomTitleViewFactory;
import com.quick.core.ui.widget.segbar.SegActionCallBack;
import com.quick.jsbridge.util.common.JsonUtil;
import com.quick.jsbridge.util.device.DeviceUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 设置多标题
 * <p>
 * 参数：
 * titles:  标题,数组
 */
public class SetMultiTitleMethod implements IApiMethod {

    @Override
    public String name() {
        return "setMultiTitle";
    }

    @Override
    public void execute(final IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {

        JSONArray itemsJsonObject = param.optJSONArray("titles");
        String[] titles = null;
        titles = JsonUtil.parseJSONArray(itemsJsonObject, titles);
        webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnClickNbTitle, callback.getPort());
        webLoader.getPageControl().getNbBar().getViewHolder().ivTitleArrow.setVisibility(View.GONE);
        webLoader.getPageControl().getNbBar().getViewHolder().nbCustomTitleLayout.removeAllViews();

        ICustomTitleViewFactory customTitleViewFactory = BaseThemeControl.getInstance().getSelectedTheme().customTitleViewFactory;
        webLoader.getPageControl().getNbBar().addNbCustomTitleView(customTitleViewFactory.getCustomTitleView(webLoader.getPageControl().getActivity(), titles, new SegActionCallBack() {
            @Override
            public void segAction(int i) {
                webLoader.getWebloaderControl().autoCallbackEvent.onClickNbTitle(i);
            }
        }));
    }
}
