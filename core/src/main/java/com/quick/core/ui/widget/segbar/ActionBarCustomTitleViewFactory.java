package com.quick.core.ui.widget.segbar;

import android.app.Activity;
import android.view.View;

/**
 * ActionBar 工厂
 */
public class ActionBarCustomTitleViewFactory implements ICustomTitleViewFactory {
    @Override
    public View getCustomTitleView(Activity activity, String[] titles, SegActionCallBack callBack) {
        return new ActionBarSeg(activity,titles,callBack).create();
    }
}
