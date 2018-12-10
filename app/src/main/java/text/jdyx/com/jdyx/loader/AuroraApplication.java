package text.jdyx.com.jdyx.loader;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 创建日期：2018/7/2 0002 on 11:08
 * 描述:极光推送
 * 作者:侯宇航 Administrator
 */
public class AuroraApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
    }
}
