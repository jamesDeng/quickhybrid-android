package com.quick.jsbridge.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

public interface IFileChooseActivity {


    /**
     * 进入文件选择界面
     *
     * @param activity
     * @param requestCode
     */
    public void goFileChooseActivity(Activity activity, int requestCode);

    /**
     * 进入文件选择界面
     *
     * @param fragment
     * @param requestCode
     */
    public void goFileChooseActivity(Fragment fragment, int requestCode);

    /**
     * 进入文件选择界面
     *
     * @param fragment
     * @param requestCode
     */
    public  void goFileChooseActivity(Object fragment, int requestCode);

    /**
     * 进入文件选择界面
     *
     * @param fragment
     * @param requestCode
     */
    public void goFileChooseActivity(android.support.v4.app.Fragment fragment, int requestCode);
}
