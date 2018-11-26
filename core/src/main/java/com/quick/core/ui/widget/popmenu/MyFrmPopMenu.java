package com.quick.core.ui.widget.popmenu;

import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class MyFrmPopMenu implements IFrmPopMenu {


    @Override
    public void show(IQuickFragment webLoader,String[] titleItems,String[] iconItems,int iconColor,final Callback callback) {
        FrmPopMenu popupWindow = new FrmPopMenu(webLoader.getPageControl().getActivity(), webLoader.getPageControl().getNbBar().getRootView(), titleItems, iconItems, new PopClickListener() {
            @Override
            public void onClick(int index) {
                Map<String, Object> object = new HashMap<>();
                object.put("which", index);
                callback.applySuccess(object);
            }
        });
        popupWindow.setIconFilterColor(iconColor);
        popupWindow.show();
    }
}
