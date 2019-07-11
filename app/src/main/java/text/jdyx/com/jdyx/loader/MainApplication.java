package text.jdyx.com.jdyx.loader;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;

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
    public static Activity mActivity;
    public static Context context;

    @Override
    public void addApplications(AppConfig appConfig) {
        context = getApplicationContext();
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
        //        初始化Toast
        ToastUtils.init(this, new ToastBlackStyle());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
