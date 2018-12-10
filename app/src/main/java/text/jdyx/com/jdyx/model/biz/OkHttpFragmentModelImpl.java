package text.jdyx.com.jdyx.model.biz;


import java.util.Map;

import text.jdyx.com.jdyx.model.callback.MyCallBack;
import text.jdyx.com.jdyx.model.entity.InfoBean;
import text.jdyx.com.jdyx.model.net.OnDownloadListener;
import text.jdyx.com.jdyx.model.net.Urls;

/**
 * 创建日期：2018/4/26 0026 on 13:56
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class OkHttpFragmentModelImpl implements IOkHttpFragmentModel {

    @Override
    public void getLoadResult(MyCallBack<InfoBean> myCallBack, Map<String, String> map) {
        iHttp.post(Urls.INFO_URL, map ,myCallBack);
    }

    @Override
    public void getDownLoadResult(String url, String fileName, OnDownloadListener onDownloadListener) {
        iHttp.downLoad(url,fileName,onDownloadListener);
    }

}
