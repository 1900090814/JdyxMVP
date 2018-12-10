package text.jdyx.com.jdyx.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;
import text.jdyx.com.jdyx.utils.LogUtil;
import text.jdyx.com.jdyx.utils.SharedPreferencesApp;


/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";


    private NotificationManager nm;
    private String message;


    @Override

    public void onReceive(Context context, Intent intent) {

        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        String pushJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
        if (pushJson != null && pushJson.length() > 5) {
            SharedPreferencesApp.setedit(context, "pushJson", pushJson);
        }

        Log.e(TAG, "onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));


        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

            Log.e(TAG, "JPush用户注册成功");


        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

            Log.e(TAG, "接受到推送下来的自定义消息");


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

            Log.e(TAG, "接受到推送下来的通知");


            receivingNotification(context, bundle);


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String sharedPushJson = (String) SharedPreferencesApp.getData(context, "pushJson", "");
            LogUtil.e("推送下来的Json", sharedPushJson);

            Log.e(TAG, "用户点击打开了通知");

            openNotification(context, bundle);


        } else {

            Log.e(TAG, "Unhandled intent - " + intent.getAction());

        }

    }


    private void receivingNotification(Context context, Bundle bundle) {

        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);

        Log.e(TAG, " title : " + title);

        message = bundle.getString(JPushInterface.EXTRA_ALERT);

        Log.e(TAG, "message : " + message);

        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

        Log.e(TAG, "extras : " + extras);


    }


    private void openNotification(Context context, Bundle bundle) {

        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";

        try {

            JSONObject extrasJson = new JSONObject(extras);

            myValue = extrasJson.optString("myKey");


        } catch (Exception e) {

            Log.w(TAG, "Unexpected: extras is not a valid json", e);

            return;

        }

    }


    // 打印所有的 intent extra 数据

    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();

    }

}