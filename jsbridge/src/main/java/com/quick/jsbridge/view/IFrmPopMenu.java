package com.quick.jsbridge.view;

import com.quick.jsbridge.bridge.Callback;

/**
 * 顶部选项按钮
 */
public interface IFrmPopMenu {

    void show(IQuickFragment webLoader,String[] titleItems,String[] iconItems,int iconColor,final Callback callback);
}
