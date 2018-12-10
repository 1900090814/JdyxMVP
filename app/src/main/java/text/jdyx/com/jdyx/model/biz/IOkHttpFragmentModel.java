package text.jdyx.com.jdyx.model.biz;


import java.util.Map;

import text.jdyx.com.jdyx.model.callback.MyCallBack;
import text.jdyx.com.jdyx.model.entity.InfoBean;
import text.jdyx.com.jdyx.model.net.OnDownloadListener;

/**
 * 创建日期：2018/4/26 0026 on 13:47
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface IOkHttpFragmentModel extends BaseModel{
    void getLoadResult(MyCallBack<InfoBean> myCallBack , Map<String ,String> map);
    void getDownLoadResult(String url, String fileName, OnDownloadListener onDownloadListener);
}
