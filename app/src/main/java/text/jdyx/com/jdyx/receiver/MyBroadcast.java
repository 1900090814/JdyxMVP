package text.jdyx.com.jdyx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by liuxiaozhu on 2017/5/11.
 * All Rights Reserved by
 * 用来监听手机时间广播（在appliytion里开启广播），每一分钟系统会发送一次
 * 该广播只需要拉起service1
 */

public class MyBroadcast extends BroadcastReceiver {
//    private HomeContract.Main main;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {//如果广播是每分钟发送一次的时间广播
            getdata(context);
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.e("timeBroad", "屏幕解锁了MyBroadcast");
            getdata(context);
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("timeBroad", "屏幕关闭了MyBroadcast");
            getdata(context);
        }
//        getdata(context);
    }
    //获得数据
    public void getdata(final Context context){
//        String openID = (String) SharedPreferencesApp.getData(context, "OpenID", "");
//        if (openID!=null) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("openID", openID);
//                jsonObject.put("items", "username|unreadLookMe|unreadMessage");//需要返回什么字段，就往里添加什么字段，不要有空格
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Map<String,String> map=new HashMap<>();
//            map.put("appid", "d272cb8bd50652c29bd458d0b5e76080");
//            Object tokenID = SharedPreferencesApp.getData(context, "TokenID", "");
//            map.put("accessToken", (String) tokenID);
//            map.put("parameters", jsonObject.toString());
//            OkHttpUtils.getInstance().post(Urls.MAINBASIC,
//                    map, new MyNetWorkCallback<ResumeIntactInfoBean>() {
//                        @Override
//                        public void onSuccess(ResumeIntactInfoBean resumeIntactInfoBean) {
//                            if (resumeIntactInfoBean.getData()!=null){
////                                LoggerUtlis.e("谁查看了我",mainBean.getData().getUnreadLookMe()+"");
//                                    if (resumeIntactInfoBean.getData().getUnreadLookMe()>0){
//                                        BadgeUtil.setBadgeCount(context, resumeIntactInfoBean.getData().getUnreadLookMe(), R.drawable.app);
//                                    }
//                                    if (resumeIntactInfoBean.getData().getUnreadLookMe()>99) {
//                                        BadgeUtil.setBadgeCount(context, 99, R.drawable.app);
//                                    }
//                                    if (resumeIntactInfoBean.getData().getUnreadLookMe()==0){
//                                        BadgeUtil.setBadgeCount(context, 0, R.drawable.app);
//                                    }
//                            }
//                        }
//                        @Override
//                        public void onError(int errorCode, String errorMsg) {
//
//                        }
//                    });
//        }
    }
}