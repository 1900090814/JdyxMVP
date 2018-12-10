package text.jdyx.com.jdyx.loader;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

/**
 * 创建日期：2018/7/2 0002 on 11:03
 * 描述:友盟
 * 作者:侯宇航 Administrator
 */
public class UmengApplication extends Application {

    {
        PlatformConfig.setWeixin("wx1686de51d47fd645", "478d7d6408040ec3b655f9b6130e060a");
        PlatformConfig.setQQZone("1106591107", "5J7K2H4cCvC3foLs");
        PlatformConfig.setSinaWeibo("2000672483", "392c81b9fdf0ae87f089a7caa0d6c9cd",
                        "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //友盟第三方登录
        Config.DEBUG = false;
    }
}
