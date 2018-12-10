package text.jdyx.com.jdyx.presenter;

import java.util.Map;

/**
 * 创建日期：2018/4/26 0026 on 10:38
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface BasePresenter {
    interface OkHttpFragmentPresenter{
        void startLoadResult(Map<String ,String> map);
        void startDownLoadResult(String url ,String fileName);
    }
}
