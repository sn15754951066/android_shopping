package com.example.android_shopping.module.home.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.login.ILogin;
import com.example.android_shopping.module.data.LoginBean;
import com.example.android_shopping.presenter.login.LoginPresenter;
import com.example.android_shopping.utils.SpUtils;
import com.luck.picture.lib.tools.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILogin.View {
    @BindView(R.id.login_btn_register)
    Button loginBtnRegister;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_edit_account)
    EditText loginEditAccount;
    @BindView(R.id.Login_Remember)
    CheckBox LoginRemember;
    @BindView(R.id.login_text_change_pwd)
    TextView loginTextChangePwd;
    @BindView(R.id.login_view)
    RelativeLayout loginView;
    @BindView(R.id.login_success_show)
    TextView loginSuccessShow;
    @BindView(R.id.login_success_view)
    RelativeLayout loginSuccessView;

    private SharedPreferences login_sp;

    @Override
    protected int getLayout() {
        return R.layout.activity_longin;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter( this );
    }


    @Override
    protected void initView() {


        login_sp = getSharedPreferences( "userInfo", 0 );
        String name = login_sp.getString( "USER_NAME", "" );
        String pwd = login_sp.getString( "PASSWORD", "" );
        boolean choseRemember = login_sp.getBoolean( "mRememberCheck", false );
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if (choseRemember) {
            loginEditAccount.setText( name );
            loginEditPwd.setText( pwd );
            LoginRemember.setChecked( true );
        }

        ClickListener mListener = new ClickListener();
        loginBtnRegister.setOnClickListener( mListener );                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        loginBtnLogin.setOnClickListener( mListener );


    }

    class ClickListener implements View.OnClickListener {

        //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_register:                  //登录界面的注册按钮
                    Intent intent = new Intent( LoginActivity.this, RegsiterActivity.class );    //切换Login Activity至User Activity
                    startActivity( intent );
                    break;
                case R.id.login_btn_login:                     //登录界面的登录按钮
                    login();
                    break;
            }
        }
    }

    @Override
    protected void initData() {


    }

    @Override
    public void getLoginReturn(LoginBean loginBean) {

        if (!TextUtils.isEmpty( loginBean.getData().getToken() )) {
            SpUtils.getInstance().setValue( "token", loginBean.getData().getToken() );
            SpUtils.getInstance().setValue( "uid", loginBean.getData().getUserInfo().getUid() );
            finish();
        }
    }

    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = loginEditAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = loginEditPwd.getText().toString().trim();
            SharedPreferences.Editor editor = login_sp.edit();

            if (!TextUtils.isEmpty( userName ) && !TextUtils.isEmpty( userPwd )) {
                presenter.getLogin( userName, userPwd );
                //保存用户名和密码
                editor.putString( "USER_NAME", userName );
                editor.putString( "PASSWORD", userPwd );

                //是否记住密码
                if (LoginRemember.isChecked()) {
                    editor.putBoolean( "mRememberCheck", true );
                } else {
                    editor.putBoolean( "mRememberCheck", false );
                }
                editor.commit();

                Toast.makeText( this, "登录成功", Toast.LENGTH_SHORT ).show();//登录成功提示

            } else {
                Toast.makeText( this, "登录失败", Toast.LENGTH_SHORT ).show();  //登录失败提示
            }


        }
    }

    public boolean isUserNameAndPwdValid() {
        if (loginEditAccount.getText().toString().trim().equals( "" )) {
            Toast.makeText( this, "用户名为空，请重新输入！", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (loginEditPwd.getText().toString().trim().equals( "" )) {
            Toast.makeText( this, "密码为空，请重新输入！", Toast.LENGTH_SHORT ).show();
            return false;
        }
        return true;
    }

}
