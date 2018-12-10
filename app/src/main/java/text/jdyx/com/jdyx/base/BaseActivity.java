package text.jdyx.com.jdyx.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import text.jdyx.com.jdyx.App;
import text.jdyx.com.jdyx.R;
import text.jdyx.com.jdyx.utils.AutoUtils;
import text.jdyx.com.jdyx.utils.NetUtil;
import text.jdyx.com.jdyx.utils.SetPopupwindow;
import text.jdyx.com.jdyx.utils.StatusBar;
import text.jdyx.com.jdyx.utils.ToastUtils;


/**
 * 创建日期：2018/4/25 0025 on 15:53
 * 描述:
 * 作者:侯宇航 Administrator
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.mActivity=this;
        App.mFragmentActivity=this;
        //沉浸式透明
        StatusBar.fullScreen(this);
        AutoUtils.auto(this);
        boolean networkAvailable = NetUtil.isNetworkConnected(this);
        if (networkAvailable == false) {
            showToast("当前网络不可用，请检查网络");
        }
        setContentView(getActivityLayout());
        initActivityView();
        initActivityListener();
        initActivityData();
    }

    protected abstract void initActivityData();

    protected abstract void initActivityListener();

    protected abstract void initActivityView();

    public abstract int getActivityLayout();

    public void showToast(String msg){
        ToastUtils.showShort(this,msg);
    };

    public PopupWindow showPopupWindow(Context context, int layout ){
        SetPopupwindow.Builder setPopupwindow=new SetPopupwindow.Builder(context);
        View contentview = LayoutInflater.from(context).inflate(layout,null);
        PopupWindow popupWindow=new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return popupWindow;
    }
    public void setTitleBar(String title){
        Toolbar toolbar = findViewById(R.id.tb_mToolbar);
        TextView tb_title = findViewById(R.id.tb_title);
        if (toolbar!=null){
           toolbar.setNavigationIcon(R.drawable.login_nav_return_n);
           toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   finish();
               }
           });
       }
       if (tb_title!=null){
            tb_title.setText(title);
       }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //把操作放在用户点击的时候
            View v = getCurrentFocus();
            //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, ev)) {
                //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());
                //收起键盘
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /** * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏 * * @param v * @param event * @return */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }
    /** * 获取InputMethodManager，隐藏软键盘 * @param token */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
