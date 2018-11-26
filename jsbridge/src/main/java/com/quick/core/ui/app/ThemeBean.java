package com.quick.core.ui.app;

import com.quick.core.ui.widget.segbar.ICustomTitleViewFactory;
import com.quick.jsbridge.view.AbstractDialog;
import com.quick.jsbridge.view.IFrmPopMenu;
import com.quick.jsbridge.view.IPrompt;
import com.quick.jsbridge.view.ITitleStyle;

/**
 * Created by dailichun on 2017/12/7.
 * 主题实体对象
 */
public class ThemeBean {

    /**
     * 主题id
     */
    public String themeId;

    /**
     * 是否默认
     */
    public Boolean defultIs = true;

    /**
     * 导航栏背景
     * 支持3种格式：
     * 1.String #RRGGBB或#AARRGGBB (Quick只能使用这种方式，且去掉#)
     * 2.int 纯数字 表示资源id,可以是Color.XX或者R.color.XX
     */
    public Object topbarImage;

    /*
     * 左侧返回按钮图片
     * 支持3种格式：
     * 1.int R.drawable.XX
     * 2.String 文件名 表示R.drawable.XX中的文件名
     * 3.网络图片 (Quick只能使用这种方式，如果加载失败则加载默认图片)
     */
    public Object topbarBackImage ;

    /**
     * 文字图片过滤色
     * 支持2种格式：
     * 1.#RRGGBB或#AARRGGBB (Quick只能使用这种方式，且去掉#)
     * 2.纯数字 表示资源id,可以是Color.XX或者R.color.XX
     */
    public Object topbarFilterColor;

    /**
     * 默认文件图片
     */
    public int defaultFileImg;

    /**
     * 导航关闭图标
     */
    public int navExitBtnImg;

    /*
     * 备用背景图片
     *
     */
    public Object otherImage;

    /*
     *备用背景图片
     */
    public Object otherImage2;

    /**
     * 标题的样式
     */
    public ITitleStyle titleStyle;

    /**
     * web load
     */
    public Class<?> quickWebLoader;

    public ICustomTitleViewFactory customTitleViewFactory;

    public AbstractDialog dialog;

    public IFrmPopMenu frmPopMenu;

    public IPrompt prompt;

    public Class<?> sacnCaptureActivity;

    public Class<?> selectFileCaptureActivity;

}
