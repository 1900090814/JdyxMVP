package text.jdyx.com.jdyx.loader;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import text.jdyx.com.jdyx.activity.LoginActivity;
import text.jdyx.com.jdyx.receiver.CallReceiver;
import text.jdyx.com.jdyx.utils.SharedPreferencesApp;


/**
 * 创建日期：2018/7/2 0002 on 10:58
 * 描述:环信
 * 作者:侯宇航 Administrator
 */
public class HuanXinApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        //        环信视频通话
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //自动登录
        options.setAutoLogin(true);
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
//        环信电话广播接收
        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        registerReceiver(new CallReceiver(), callFilter);
    }
    //异地登录接口
    private class MyConnectionListener implements EMConnectionListener {
        public MyConnectionListener() {
        }

        @Override
        public void onConnected() {
        }

        @Override
        public void onDisconnected(final int error) {
//            LoggerUtlis.e("环信登录状态",error+"______"+EMError.USER_LOGIN_ANOTHER_DEVICE+"______"+EMError.USER_REMOVED);
            if (error == EMError.USER_REMOVED) {
                // 显示帐号已经被移除
//                LoggerUtlis.e("环信登录状态","帐号已经被移除");
            } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                // 显示帐号在其他设备登录
                EMClient.getInstance().logout(true);
                onConnectionConflict();
            }
        }
        protected void onConnectionConflict() {
            SharedPreferencesApp.getDelete(mContext);
            SharedPreferencesApp.setedit(mContext, "Guide", true);
            Intent intent = new Intent(mContext, LoginActivity.class);
            //登录冲突
            intent.putExtra("loginState", "Signout");
            mContext.startActivity(intent);
        }
    }
}
