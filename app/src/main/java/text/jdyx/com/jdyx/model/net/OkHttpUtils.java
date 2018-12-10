package text.jdyx.com.jdyx.model.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import text.jdyx.com.jdyx.App;
import text.jdyx.com.jdyx.model.callback.MyCallBack;
import text.jdyx.com.jdyx.utils.Constants;
import text.jdyx.com.jdyx.utils.LogUtil;

import static text.jdyx.com.jdyx.utils.Constants.DOWNLOAD_PROGRESS;


/**
 * 创建日期：2018/4/26 0026 on 9:16
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class OkHttpUtils implements IHttp{
    private OkHttpClient okHttpClient;
    private static OkHttpUtils utils;
    private OnDownloadListener onDownloadListener;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.DOWNLOAD_PROGRESS:
                    onDownloadListener.onDownloading((Integer) msg.obj);
                    break;
                case Constants.DOWNLOAD_FAIL:
                    onDownloadListener.onDownloadFailed();
                    break;
                case Constants.DOWNLOAD_SUCCESS:
                    onDownloadListener.onDownloadSuccess((String) msg.obj);
                    break;
            }
        }
    };


    private OkHttpUtils() {
        //创建Cache对象，并设置缓存路径以及缓存区域大小
        Cache cache = new Cache(App.mActivity.getCacheDir(), 10 * 1024 * 1024);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)//请求超时时间
                .cache(cache)//设置缓存
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getInterceptor())
                .build();
    }

    public Interceptor getInterceptor(){
        /***
         * 拦截器，保存缓存的方法
         * 2016年7月29日11:22:47
         */
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                boolean netAvailable = NetworkUtil.isNetAvailable(App.context);
                if (netAvailable) {
                    Response response = chain.proceed(request);
                    int maxAge = 6; // 在线缓存在1分钟内可读取
                    String cacheControl = request.cacheControl().toString();
                    Log.e("缓存","在线缓存在1分钟内可读取："+cacheControl);
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)//或者直接用系统的
                            .build();

                    Response response = chain.proceed(request);
                    //下面注释的部分设置也没有效果，因为在上面已经设置了
                    return response.newBuilder()
                            //.removeHeader("Pragma")
                            //.removeHeader("Cache-Control")
                            //.header("Cache-Control", "public, only-if-cached, max-stale=50")
                            .build();
                }
            }
        };
        return interceptor;
    }

    public static synchronized OkHttpUtils getInstance(){
        if (utils==null){
            utils=new OkHttpUtils();
        }
        return utils;
    }

    public <T> void get(String url , Map<String,String> map , final MyCallBack<T> myCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null&&map.size()>0){
            for (Map.Entry<String,String> entry:map.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        final Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String jsonData = response.body().string();
                App.mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            myCallBack.onSuccess(getGeneric(jsonData,myCallBack));
                    }
                });
            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> params, final MyCallBack<T> myCallBack) {
        FormBody.Builder builder=new FormBody.Builder();
        StringBuffer sb=new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()+"="+entry.getValue()+"&");
                builder.add(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        LogUtil.e("网络地址",url+"?"+sb.toString());
        CacheControl.Builder cacheBuilder =
                new CacheControl.Builder().
                        maxAge(6, TimeUnit.SECONDS).//这个是控制缓存的最大生命时间
                        maxStale(6,TimeUnit.SECONDS);//这个是控制缓存的过时时间
        final Request request=new Request.Builder().url(url).post(builder.build()).cacheControl(cacheBuilder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e.toString());
                    }
                });
            }

            @Override
            public void onResponse( Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                App.mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.e("JSON串",jsonData);
                        myCallBack.onSuccess(getGeneric(jsonData,myCallBack));
                    }
                });
            }
        });
    }

    @Override
    public void downLoad(final String url, final String fileName, OnDownloadListener onDownloadListener) {
        this.onDownloadListener=onDownloadListener;
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=Message.obtain();
                message.what=Constants.DOWNLOAD_FAIL;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is=null;
                byte[] buf=new byte[2048];
                int len=0;
                FileOutputStream fos=null;
                //储存下载文件的目录
                String savePath=isExistDir(Constants.DOWN_LOAD_VIDEO);
                try{
                    is=response.body().byteStream();
                    long total=response.body().contentLength();
                    LogUtil.e("下载视频的名字",fileName);
                    File file=new File(savePath,fileName);
                    fos=new FileOutputStream(file);
                    long sum=0;
                    while((len = is.read(buf))!=-1){
                        fos.write(buf,0,len);
                        sum+=len;
                        int progress=(int)(sum*1.0f/total*100);
                        //下载中
                        Message message=Message.obtain();
                        message.what=DOWNLOAD_PROGRESS;
                        message.obj=progress;
                        mHandler.sendMessage(message);
                    }
                    fos.flush();
                    //下载完成
                    Message message= Message.obtain();
                    message.what=Constants.DOWNLOAD_SUCCESS;
                    message.obj=file.getAbsolutePath();
                    mHandler.sendMessage(message);
                }catch (Exception e){
                    Message message=Message.obtain();
                    message.what=Constants.DOWNLOAD_FAIL;
                    mHandler.sendMessage(message);
                }finally{
                    try{
                        if(is!=null)
                            is.close();
                    }catch (IOException e){

                    }
                    try {
                        if(fos!=null){
                            fos.close();
                        }
                    }catch (IOException e){

                    }
                }
            }
        });
    }
    private String isExistDir(String saveDir) throws IOException {
        File downloadFile=new File(saveDir);
        if(!downloadFile.mkdirs()){
            downloadFile.createNewFile();
        }
        String savePath=downloadFile.getAbsolutePath();
        return savePath;
    }

    private <T> T getGeneric(String jsonData,MyCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];

        T t = gson.fromJson(jsonData,type);
        return t;
    }

}
