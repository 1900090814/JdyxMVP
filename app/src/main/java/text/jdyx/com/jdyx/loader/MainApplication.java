package text.jdyx.com.jdyx.loader;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;

import text.jdyx.com.jdyx.base.BaseFragment;
import text.jdyx.com.jdyx.loader.hookapp.AppConfig;
import text.jdyx.com.jdyx.loader.hookapp.HookApplication;
import text.jdyx.com.jdyx.utils.AutoOptions;
import text.jdyx.com.jdyx.utils.AutoUtils;


/**
 * 创建日期：2018/7/2 0002 on 10:53
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class MainApplication extends HookApplication {
    public static FragmentActivity mBaseActivity;
    public static BaseFragment myFragment;

    @Override
    public void addApplications(AppConfig appConfig) {
        appConfig.add(new BuglyApplication());
        appConfig.add(new HuanXinApplication());
        appConfig.add(new UmengApplication());
        appConfig.add(new AuroraApplication());
//        appConfig.add(new QiNiuApplication());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //屏幕适配方案
        AutoOptions autoOptions = new AutoOptions.Builder().init(this).setAutoType(AutoOptions.AutoType.DP_2).setHasStatusBar(true).setDesign(720, 1280).build();
        AutoUtils.setAutoOptions(autoOptions);

//        //外层红点
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(Intent.ACTION_TIME_TICK);//系统时间，每分钟发送一次
//        intentFilter.addAction(Intent.ACTION_SCREEN_ON);//屏幕打开（解锁），
//        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);//屏幕关闭
//        MyBroadcast myBroadcast = new MyBroadcast();
//        registerReceiver(myBroadcast, intentFilter);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
