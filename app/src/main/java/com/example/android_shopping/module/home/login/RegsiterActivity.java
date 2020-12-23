package com.example.android_shopping.module.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.regsiter.IRegsiter;
import com.example.android_shopping.module.data.RegisterBean;
import com.example.android_shopping.presenter.regsiter.RegieterPresenter;
import com.example.android_shopping.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegsiterActivity extends BaseActivity<RegieterPresenter> implements IRegsiter.View {
    @BindView(R.id.resetpwd_edit_name)
    EditText resetpwdEditName;
    @BindView(R.id.resetpwd_edit_pwd_old)
    EditText resetpwdEditPwdOld;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.fl_pass)
    FrameLayout flPass;
    @BindView(R.id.resetpwd_edit_pwd_new)
    EditText resetpwdEditPwdNew;
    @BindView(R.id.register_btn_sure)
    Button registerBtnSure;
    @BindView(R.id.register_btn_cancel)
    Button registerBtnCancel;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected RegieterPresenter createPrenter() {
        return new RegieterPresenter( this );
    }

    @Override
    protected void initView() {
        imgPw.setTag( 1 );
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.register_btn_sure, R.id.register_btn_cancel, R.id.img_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_btn_sure:
                register_check();
                break;
            case R.id.register_btn_cancel:
                startActivity( new Intent( RegsiterActivity.this, LoginActivity.class ) );
                finish();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if (tag == 1) {
                    imgPw.setImageResource( R.drawable.eyes );
                    imgPw.setTag( 2 );
                    resetpwdEditPwdOld.setTransformationMethod( HideReturnsTransformationMethod.getInstance() );
                } else {
                    imgPw.setImageResource( R.drawable.close_eyes );
                    imgPw.setTag( 1 );
                    resetpwdEditPwdOld.setTransformationMethod( PasswordTransformationMethod.getInstance() );
                }
                break;
        }
    }

    public void register_check() {                                //确认按钮的监听事件
        if (isUserNameAndPwdValid()) {
            String userName = resetpwdEditName.getText().toString();
            String userPwd = resetpwdEditPwdOld.getText().toString();
            String userPwdCheck = resetpwdEditPwdNew.getText().toString();

            if (!TextUtils.isEmpty( userName ) && !TextUtils.isEmpty( userPwd ) && !TextUtils.isEmpty( userPwdCheck )) {
                if (userPwd.equals( userPwdCheck )) {
                    presenter.getRegsiter( userName, userPwd );
                } else {
                    Toast.makeText( this, "密码不一致", Toast.LENGTH_SHORT ).show();
                }
            } else {
                Toast.makeText( this, "注册失败", Toast.LENGTH_SHORT ).show();
            }

            if (userPwd.equals( userPwdCheck ) == false) {     //两次密码输入不一样
                Toast.makeText( this, "两次密码输入不一致", Toast.LENGTH_SHORT ).show();
                return;
            }
        }
    }

    public boolean isUserNameAndPwdValid() {
        if (resetpwdEditName.getText().toString().trim().equals( "" )) {
            Toast.makeText( this, "用户名为空，请重新输入！", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (resetpwdEditPwdOld.getText().toString().trim().equals( "" )) {
            Toast.makeText( this, "密码为空，请重新输入！", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (resetpwdEditPwdNew.getText().toString().trim().equals( "" )) {
            Toast.makeText( this, "密码确认为空，请重新输入！", Toast.LENGTH_SHORT ).show();
            return false;
        }
        return true;
    }

    @Override
    public void getRegsiterReturn(RegisterBean registerBean) {
        int errno = registerBean.getErrno();

        if(errno==0){
            Toast.makeText( this, "注册成功！", Toast.LENGTH_SHORT ).show();
            finish();
        }else if(errno!=0){
            Toast.makeText( this, "用户名已注册,请重新注册！", Toast.LENGTH_SHORT ).show();
        }

    }
}
