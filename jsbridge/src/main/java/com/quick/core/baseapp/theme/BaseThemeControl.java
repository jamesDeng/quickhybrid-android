package com.quick.core.baseapp.theme;

import com.quick.core.ui.app.IThemeControl;
import com.quick.core.ui.app.ThemeBean;

import java.util.ArrayList;
import java.util.List;

import quick.com.jsbridge.R;


/**
 * Created by dailichun on 2017/12/7.
 * 主题配置类
 */
public class BaseThemeControl implements IThemeControl {

    private List<ThemeBean> themes;

    private static BaseThemeControl ourInstance;

    public static BaseThemeControl getInstance() {
        if (ourInstance == null) {
            ourInstance = new BaseThemeControl();
        }
        return ourInstance;
    }

    /**
     * 初始化设置指定主题
     *
     * @param themeList
     */
    @Override
    public void initTheme(List<ThemeBean> themeList) {
        if (themeList != null && themeList.size() > 0) {
            themes = themeList;
            setThemeId(themes.get(0).themeId);
        }
    }

    /**
     * 设置第一个主题为当前用户选择的主题
     */
    @Override
    public void setThemeId(String id) {

        // 可以保持数据库，默认不做
    }

    @Override
    public String getThemeId() {

        // 默认就是默认主题
        return "theme_default_blue";
    }

    /**
     * 获取设置的主题
     *
     * @return
     */
    @Override
    public List<ThemeBean> getTheme() {
        return themes;
    }

    /**
     * 获取本用户保存主题的key值
     *
     * @return
     */
    @Override
    public String getThemeKey() {
        return "theme_default_blue";
    }

    /**
     * 获取当前主题
     *
     * @return
     */
    @Override
    public ThemeBean getSelectedTheme() {
        if (themes == null) {
            throw new RuntimeException("未设置主题");
        }
        ThemeBean cuTheme = null;
        for (ThemeBean theme : themes) {
            if (theme.defultIs) {
                cuTheme = theme;
            }
        }
        return cuTheme;
    }

}
