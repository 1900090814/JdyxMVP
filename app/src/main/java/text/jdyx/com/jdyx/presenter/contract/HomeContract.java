package text.jdyx.com.jdyx.presenter.contract;


import text.jdyx.com.jdyx.model.entity.InfoBean;
import text.jdyx.com.jdyx.presenter.BasePresenter;
import text.jdyx.com.jdyx.view.BaseView;

/**
 * 创建日期：2018/4/26 0026 on 10:37
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface HomeContract {
    interface OkhttpFragmentView extends BaseView<OkhttpFragmentPresenter> {
        void setMiainActivityResult(InfoBean mainActivityBean);
        void setErrorMsg(String errorMsg);
    }
    interface OkhttpFragmentPresenter extends BasePresenter.OkHttpFragmentPresenter {
    }
}
