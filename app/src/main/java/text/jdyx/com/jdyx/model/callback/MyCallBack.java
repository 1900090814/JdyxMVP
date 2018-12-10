package text.jdyx.com.jdyx.model.callback;

/**
 * 创建日期：2018/4/26 0026 on 9:41
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface MyCallBack<T>{
    void onError(String error);
    void onSuccess(T t);
}
