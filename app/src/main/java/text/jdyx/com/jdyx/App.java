package text.jdyx.com.jdyx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import text.jdyx.com.jdyx.base.BaseFragment;


/**
 * 创建日期：2018/4/25 0025 on 15:53
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class App extends Application {
    public static Activity mActivity;
    public static BaseFragment mFragment;
    public static FragmentActivity mFragmentActivity;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
