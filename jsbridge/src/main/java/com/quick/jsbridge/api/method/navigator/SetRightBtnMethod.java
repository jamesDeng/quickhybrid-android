package com.quick.jsbridge.api.method.navigator;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quick.core.ui.app.INbControl;
import com.quick.jsbridge.util.app.AppUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONObject;

/**
 * 设置导航栏最右侧按钮
 * <p>
 * 参数：
 * isShow：是否显示，0：隐藏 其他：显示
 * text：文字按钮
 * imageUrl:图片按钮，格式为url地址,优先级高
 */
public class SetRightBtnMethod implements IApiMethod {

    @Override
    public String name() {
        return "setRightBtn";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        int which = param.optInt("which", 0);
        boolean isShow = !"0".equals(param.optString("isShow"));
        String text = param.optString("text");
        String imageUrl = param.optString("imageUrl");
        INbControl.ViewHolder holder = webLoader.getPageControl().getNbBar().getViewHolder();
        if (isShow) {
            if (!TextUtils.isEmpty(imageUrl)) {
                //设置图片
                holder.nbRightIvs[which].setVisibility(View.INVISIBLE);
                holder.nbRightTvs[which].setVisibility(View.INVISIBLE);
                ImageLoader.getInstance().displayImage(imageUrl, holder.nbRightIvs[which], AppUtil.getImageLoaderOptions(0, 0, true, true));
                holder.nbRightIvs[which].setVisibility(View.VISIBLE);
            } else {
                //设置文字
                holder.nbRightIvs[which].setVisibility(View.INVISIBLE);
                holder.nbRightTvs[which].setVisibility(View.VISIBLE);
                holder.nbRightTvs[which].setText(text);
            }
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnClickNbRight + which, callback.getPort());
        } else {
            //隐藏按钮
            holder.nbRightTvs[which].setVisibility(View.INVISIBLE);
            holder.nbRightIvs[which].setVisibility(View.INVISIBLE);
            webLoader.getWebloaderControl().removePort(AutoCallbackDefined.OnClickNbRight + which);
        }
    }
}
