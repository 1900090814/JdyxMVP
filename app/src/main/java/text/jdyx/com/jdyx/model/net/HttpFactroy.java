package text.jdyx.com.jdyx.model.net;

/**
 * 创建日期：2018/4/26 0026 on 10:25
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class HttpFactroy {
    public static IHttp create(){
        return OkHttpUtils.getInstance();
    }
}
