package com.quick.jsbridge.view;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public interface IPrompt {


    EditText findEditText(View view);

    View findView(Context context);
}
