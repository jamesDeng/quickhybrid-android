package com.quick.jsbridge.api;

import com.quick.core.ui.widget.segbar.ICustomTitleViewFactory;
import com.quick.jsbridge.api.method.navigator.HideBackButtonMethod;
import com.quick.jsbridge.api.method.navigator.HideMethod;
import com.quick.jsbridge.api.method.navigator.HideStatusBarMethod;
import com.quick.jsbridge.api.method.navigator.HookBackBtnMethod;
import com.quick.jsbridge.api.method.navigator.HookSysBackMethod;
import com.quick.jsbridge.api.method.navigator.SetLeftBtnMethod;
import com.quick.jsbridge.api.method.navigator.SetMultiTitleMethod;
import com.quick.jsbridge.api.method.navigator.SetRightBtnMethod;
import com.quick.jsbridge.api.method.navigator.SetTitleMethod;
import com.quick.jsbridge.api.method.navigator.ShowMethod;
import com.quick.jsbridge.api.method.navigator.ShowStatusBarMethod;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.bridge.IBridgeImpl;
import com.quick.jsbridge.view.ITitleStyle;

import java.util.Arrays;
import java.util.List;


/**
 * Created by dailichun on 2017/12/6.
 */
public class NavigatorApi implements IBridgeImpl {

    /**
     * 注册API的别名
     */
    public static String RegisterName = "navigator";


    @Override
    public String getRegisterName() {
        return RegisterName;
    }

    @Override
    public List<IApiMethod> methods() {
        return Arrays.asList(
                new HideBackButtonMethod(),
                new HideMethod(),
                new HideStatusBarMethod(),
                new HookBackBtnMethod(),
                new HookSysBackMethod(),
                new SetLeftBtnMethod(),
                new SetMultiTitleMethod(),
                new SetRightBtnMethod(),
                new SetTitleMethod(),
                new ShowMethod(),
                new ShowStatusBarMethod()
        );
    }
}
