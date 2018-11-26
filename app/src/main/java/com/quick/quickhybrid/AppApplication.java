package com.quick.quickhybrid;


import com.quick.core.application.FrmApplication;
import com.quick.core.baseapp.baseactivity.QuickWebLoader;
import com.quick.core.baseapp.component.FileChooseActivity;
import com.quick.core.baseapp.theme.BaseThemeControl;
import com.quick.core.ui.app.ThemeBean;
import com.quick.core.ui.widget.DefultTitleStyle;
import com.quick.core.ui.widget.MyPrompt;
import com.quick.core.ui.widget.popmenu.MyFrmPopMenu;
import com.quick.core.ui.widget.segbar.ActionBarCustomTitleViewFactory;
import com.quick.jsbridge.util.common.DefaultDialog;
import com.quick.jsbridge.bridge.JSBridge;
import com.quick.core.baseapp.component.scan.ScanCaptureActivity;

import java.util.Arrays;


public class AppApplication extends FrmApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.BUILD_TYPE.equals("release")) {
            // 可以防止二次打包

        } else {

            //保存所有log日志，调试时可开启，正式包不允许开启
            // LogUtil.autoLog();

            //开启ButterKnife的日志输出
//            ButterKnife.setDebug(true);

            //是否开启下载日志
//            FileDownloadLog.NEED_LOG = true;


        }

        ThemeBean defaultItem = new ThemeBean();
        defaultItem.themeId = "theme_default_blue";
        defaultItem.topbarImage = quick.com.core.R.color.white;
        defaultItem.topbarBackImage = quick.com.core.R.mipmap.img_back_nav_btn;
        defaultItem.topbarFilterColor = quick.com.core.R.color.nbbar_bg_blue;
        defaultItem.defaultFileImg = quick.com.core.R.mipmap.img_default;
        defaultItem.navExitBtnImg = quick.com.core.R.mipmap.img_exit_nav_btn;
        defaultItem.titleStyle = new DefultTitleStyle();
        defaultItem.quickWebLoader = QuickWebLoader.class;
        defaultItem.customTitleViewFactory = new ActionBarCustomTitleViewFactory();
        defaultItem.dialog = new DefaultDialog();
        defaultItem.frmPopMenu = new MyFrmPopMenu();
        defaultItem.prompt = new MyPrompt();
        defaultItem.sacnCaptureActivity = ScanCaptureActivity.class;
        defaultItem.selectFileCaptureActivity = FileChooseActivity.class;

        //初始化默认主题
        BaseThemeControl.getInstance().initTheme(Arrays.asList(defaultItem));

    }

}
