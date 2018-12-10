package text.jdyx.com.jdyx.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import text.jdyx.com.jdyx.loader.MainApplication;
import text.jdyx.com.jdyx.utils.NetUtil;


public abstract class BaseFragment extends Fragment {

    private Bundle bundle;
    private View rootView;
    private boolean isFirstVisible;
    private boolean isFragmentVisible;
    private boolean isReuseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainApplication.myFragment = this;
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        initFragmentView(view);
        initFragmentData();
        initFragmentListener();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView && rootView != null ? rootView : view, savedInstanceState);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    protected void onFragmentVisibleChange(boolean isVisible){}

    protected abstract void initFragmentListener();

    /**
     * 在fragment首次可见时回调，可用于加载数据，防止每次进入都重复加载数据
     */
    protected void onFragmentFirstVisible() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        //当前Fragment处于隐藏状态
        if (hidden) {
            onHidden();
        } else {//当前Fragment处于显示状态
            onShow();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    //加载布局
    protected abstract int getFragmentLayoutId();

    //初始化数据
    protected abstract void initFragmentView(View view);

    //加载数据  点击事件
    protected abstract void initFragmentData();

    //该方法在Fragment可见时调用，可以在该方法中刷新数据
    protected void onShow() {
        boolean networkAvailable = NetUtil.isNetworkConnected(getContext());
        if (networkAvailable == false) {
            showToast("当前网络不可用，请检查网络");
        }
    }

    //该方法在Fragment隐藏时调用，可以做数据保存
    protected void onHidden() {
        stopLoadData();
    }

    protected  void stopLoadData(){}

    protected void showToast(String hint) {
        Toast toast=null;
        if (toast == null) {
            toast = Toast.makeText(getContext(), hint, Toast.LENGTH_SHORT);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
    protected void hideEditText(EditText editText){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }



    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }
    /**
     * 获取当前版本号
     */
    public String getAppVersionName() {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm =  getActivity().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
//            LoggerUtlis.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp =  getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }


    protected void initVariable(){
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }
    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }
}
