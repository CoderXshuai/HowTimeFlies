package com.example.howtimeflies.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.register_username)
    EditText registerUsername;  //用户名
    @BindView(R.id.register_password)
    PasswordEditText registerPassword;  //密码
    @BindView(R.id.password_again)
    PasswordEditText passwordAgain;     //重复密码
    @BindView(R.id.register_submit_button)
    ButtonView registerSubmitButton;    //注册
    @BindView(R.id.register_cancel_button)
    ButtonView registerCancelButton;    //重置

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page_layout);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @OnClick({R.id.register_submit_button, R.id.register_cancel_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_submit_button:
                if (registerPassword.getText().toString().equals(passwordAgain.getText().toString())) {
                    System.out.println("密码相等");
                    registerMethod();   //注册
                } else {
                    showMessage("两次输入密码不相等");
                }
                break;
            case R.id.register_cancel_button:
                rest(); //重置
                break;
        }

    }

    /**
     * 注册事件 讲注册的用户保存到LeanCloud数据库中
     */
    private void registerMethod() {

        // 创建实例
        AVUser user = new AVUser();

        // 等同于 user.put("username", "Tom")
        user.setUsername(registerUsername.getText().toString());
        user.setPassword(registerPassword.getText().toString());

        // 设置其他属性的方法跟 AVObject 一样
        user.put("gender", "secret");

        user.signUpInBackground().subscribe(new Observer<AVUser>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            //注册成功
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onNext(AVUser avUser) {
                System.out.println("注册成功, objectId:"+ avUser.getObjectId());
                //跳转到登录
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
            }

            //注册失败
            @Override
            public void onError(Throwable e) {
                System.out.println("注册失败,"+e.getMessage());
                showMessage("已有相同用户名");
            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 重置
     */
    private void rest() {
        registerUsername.setText("");
        registerPassword.setText("");
        passwordAgain.setText("");
    }




    /**
     * 获取对象
     */
    public void getById() {
        AVQuery<AVObject> query = new AVQuery<>("TestObject");
        query.getInBackground("5eed7afebb85df0006a32390").subscribe(new Observer<AVObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AVObject avObject) {
                // avObject 就是 objectId 为 582570f38ac247004f39c24b 的 TestObject 实例
                String words = avObject.getString("words");

                // 获取内置属性
                String objectId = avObject.getObjectId();
                Date createAt = avObject.getCreatedAt();    //创建日期
                Date updateAt = avObject.getUpdatedAt();    //最新更新日期

                System.out.println("objectId:" + objectId + "\n创建日期:" + createAt.toString() + "\n更新日期:" + updateAt.toString() + "\nwords:" + words);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
    }
}