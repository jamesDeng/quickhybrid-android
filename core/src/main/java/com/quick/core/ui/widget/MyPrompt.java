package com.quick.core.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.quick.jsbridge.view.IPrompt;

import quick.com.core.R;

public class MyPrompt implements IPrompt {

    @Override
    public EditText findEditText(View view) {
        return (EditText) view.findViewById(R.id.et);
    }

    @Override
    public View findView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.frm_prompt, null);
    }
}
