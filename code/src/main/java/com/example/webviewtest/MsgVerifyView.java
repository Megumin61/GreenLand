package com.example.webviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * AppCompatActivity可以改成自定义的基类
 * @author passerbyYSQ
 * @create 2020-05-01 14:56
 */
public abstract class MsgVerifyView extends AppCompatActivity {

    private String phone;

    private EventHandler eventHandler;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            int event = message.arg1;
            int result = message.arg2;
            Object data = message.obj;
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //View view = MsgVerifyHelper.this.view;
                switch (event) {
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE: {
                        // 发送验证码成功
                        onSendCodeSucceeded();
                        break;
                    }
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE: {
                        // 验证成功
                        onVerifySucceeded();
                        break;
                    }
                    case SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES: {
                        //返回支持发送验证码的国家列表
                        // 可能是号码填写错误，导致发送失败
                        // 前端验证把这个给排除掉了
                        onSendCodeFailed();
                        break;
                    }
                }
            }else{
                //((Throwable)data).printStackTrace();
                // 验证码错误
                onVerifyFailed();
            }
            return true;
        }
    });

    // 使用MobTech的短信推送，需要同意隐私授权。在前端界面需要强制用户同意
    // 不同意不能注册
    static {
        MobSDK.submitPolicyGrantResult(true, null);
    }

    // 获取验证码
    public void getCode(String phone) {
        if (checkPhoneNum(phone)) {
            // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
            SMSSDK.getVerificationCode("86", phone);
            // 将手机号存起来
            this.phone = phone;
        }
    }

    // 验证
    public void verifyCode(String code) {
        if (!TextUtils.isEmpty(this.phone)) {
            SMSSDK.submitVerificationCode("86", this.phone, code);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventHandler=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);

            }
        };

        //注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销实践监听，防止内存泄漏
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // 先检查手机号码是否正确。界面检查手机号时怎么给提示我不管，只需最后给我返回boolean
    protected abstract boolean checkPhoneNum(String phone);

    // 发送验证码成功
    protected abstract void onSendCodeSucceeded();
    // 发送失败（手机号不支持。一般不会发生）
    protected abstract void onSendCodeFailed();

    // 验证成功
    protected abstract void onVerifySucceeded();
    // 验证失败
    protected abstract void onVerifyFailed();

}
