package text.jdyx.com.jdyx.presenter;

import android.widget.Toast;

import java.util.Map;

import text.jdyx.com.jdyx.App;
import text.jdyx.com.jdyx.model.biz.IOkHttpFragmentModel;
import text.jdyx.com.jdyx.model.biz.OkHttpFragmentModelImpl;
import text.jdyx.com.jdyx.model.callback.MyCallBack;
import text.jdyx.com.jdyx.model.entity.InfoBean;
import text.jdyx.com.jdyx.model.net.OnDownloadListener;
import text.jdyx.com.jdyx.presenter.contract.HomeContract;
import text.jdyx.com.jdyx.utils.LogUtil;


/**
 * 创建日期：2018/4/26 0026 on 13:42
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class OkHttpFragmentPresenter implements HomeContract.OkhttpFragmentPresenter{
    private HomeContract.OkhttpFragmentView mainActivityView;
    private IOkHttpFragmentModel mainModel;

    public OkHttpFragmentPresenter(HomeContract.OkhttpFragmentView mainActivityView) {
        this.mainActivityView=mainActivityView;
        mainActivityView.setPresenter(this);
        mainModel = new OkHttpFragmentModelImpl();
    }

    @Override
    public void startLoadResult(Map<String, String> map) {
        mainModel.getLoadResult(new MyCallBack<InfoBean>() {
            @Override
            public void onError(String error) {
                mainActivityView.setErrorMsg(error);
            }

            @Override
            public void onSuccess(InfoBean infoBean) {
                mainActivityView.setMiainActivityResult(infoBean);
            }
        },map);
    }

    @Override
    public void startDownLoadResult(String url, String fileName) {
        mainModel.getDownLoadResult(url,fileName ,new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {
                LogUtil.e("下载",path);
            }

            @Override
            public void onDownloading(int progress) {
                LogUtil.e("下载",progress+"");
                if (progress>99){
                    Toast.makeText(App.mActivity, "下载成功！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDownloadFailed() {
                LogUtil.e("下载","失败");
            }
        });
    }

}
