package text.jdyx.com.jdyx.loader.hookapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * 创建日期：2018/7/2 0002 on 10:53
 * 描述:
 * 作者:侯宇航 Administrator
 */

public abstract class HookApplication extends MultiDexApplication {

	public abstract void addApplications(AppConfig appConfig);
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
		AppManager.init(this);
		addApplications(new AppConfig());
		AppManager.attachBaseContext();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		AppManager.onCreate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		AppManager.onTerminate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		AppManager.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		AppManager.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		AppManager.onTrimMemory(level);
	}
}
