package com.quick.jsbridge.api.method.navigator;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.core.ui.app.INbControl;
import com.quick.core.ui.widget.DrawableText;
import com.quick.jsbridge.util.app.AppUtil;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.control.AutoCallbackDefined;
import com.quick.jsbridge.view.IQuickFragment;
import com.quick.jsbridge.view.ITitleStyle;

import org.json.JSONObject;

import quick.com.jsbridge.R;

/**
 * 设置导航栏左侧按钮
 * <p>
 * 参数：
 * isShow：是否显示，0：隐藏 其他：显示
 * text：文字按钮
 * imageUrl:图片按钮，格式为url地址,优先级高
 * isShowArrow:是否显示箭头，最左侧文字才能显示，会替换返回按钮 1:是 其他：否
 * direction：箭头方向 top：向上 bottom：向下
 */
public class SetLeftBtnMethod implements IApiMethod {

    @Override
    public String name() {
        return "setLeftBtn";
    }

    @Override
    public void execute(IQuickFragment webLoader, WebView wv, JSONObject param, Callback callback) {
        boolean isShow = !"0".equals(param.optString("isShow"));
        String text = param.optString("text");
        String imageUrl = param.optString("imageUrl");
        boolean isShowArrow = "1".equals(param.optString("isShowArrow", "0"));
        String direction = param.optString("direction", "bottom");
        INbControl.ViewHolder holder = webLoader.getPageControl().getNbBar().getViewHolder();
        if (isShow) {
            if (!TextUtils.isEmpty(imageUrl)) {
                //设置图片
                holder.nbLeftIv2.setVisibility(View.GONE);
                holder.nbLeftTv2.setVisibility(View.GONE);
                holder.nbLeftTv1.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage(imageUrl, holder.nbLeftIv2, AppUtil.getImageLoaderOptions(0, 0, true, true));
                holder.nbLeftIv2.setVisibility(View.VISIBLE);
            } else {
                //设置文字
                if (isShowArrow) {
                    holder.nbBack.setVisibility(View.GONE);
                    holder.nbLeftTv1.setText(text);
                    if ("bottom".equals(direction)) {

                        holder.nbLeftTv1.setDrawable(BaseThemeControl.getInstance().getSelectedTheme().titleStyle.img_arrow_blue_down(), DrawableText.DIRECTION_RIGHT);
                    } else {
                        holder.nbLeftTv1.setDrawable(BaseThemeControl.getInstance().getSelectedTheme().titleStyle.img_arrow_blue_up(), DrawableText.DIRECTION_RIGHT);
                    }
                    holder.nbLeftTv1.setVisibility(View.VISIBLE);
                    holder.nbLeftIv2.setVisibility(View.GONE);
                } else {
                    holder.nbLeftIv2.setVisibility(View.GONE);
                    holder.nbLeftTv2.setVisibility(View.VISIBLE);
                    holder.nbLeftTv2.setText(text);
                }
            }
            webLoader.getWebloaderControl().addPort(AutoCallbackDefined.OnClickNbLeft, callback.getPort());
        } else {
            //隐藏按钮
            holder.nbLeftTv2.setVisibility(View.GONE);
            holder.nbLeftIv2.setVisibility(View.GONE);
            holder.nbLeftTv1.setVisibility(View.GONE);
            webLoader.getWebloaderControl().removePort(AutoCallbackDefined.OnClickNbLeft);
        }
    }
}
