package text.jdyx.com.jdyx.model.net;


import java.util.Map;

import text.jdyx.com.jdyx.model.callback.MyCallBack;

/**
 * 创建日期：2018/4/26 0026 on 10:22
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface IHttp {
    <T>void get(String url, Map<String, String> params, MyCallBack<T> myCallBack);
    <T>void post(String url, Map<String, String> params, MyCallBack<T> myCallBack);
    void downLoad(String url, String fileName, OnDownloadListener onDownloadListener);
}
