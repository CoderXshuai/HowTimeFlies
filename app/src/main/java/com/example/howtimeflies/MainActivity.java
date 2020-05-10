package com.example.howtimeflies;

import android.os.Bundle;
import android.view.View;

import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.XUI;

/**
 * @author coderXshuai
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
    }

    @Override
    public void onClick(View v) {

    }
}
