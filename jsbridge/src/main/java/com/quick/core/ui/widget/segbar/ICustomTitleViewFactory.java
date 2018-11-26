package com.quick.core.ui.widget.segbar;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 自定义的标题工厂
 */
public interface ICustomTitleViewFactory {

    View getCustomTitleView(Activity activity, String[] titles, SegActionCallBack callBack);
}
