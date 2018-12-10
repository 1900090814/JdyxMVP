package text.jdyx.com.jdyx.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 创建日期：2018/4/25 0025 on 16:02
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class SetPopupwindow {
    private static Context mContext;
    private final View contentview;
    private final PopupWindow window;

    //    Context context;
    public SetPopupwindow(Builder b){

        contentview = LayoutInflater.from(mContext).inflate(b.contentviewid,null);
        setBackgroundAlpha(0.5f);


        //创建PopupWindow对象，指定宽度和高度
        window = new PopupWindow(contentview, b.width, b.height);
        //  设置动画
//        window.setAnimationStyle(R.style.popup_window_anim);
        // 设置背景颜色
        if (b.color!=null){
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(b.color)));
        }else{
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        }


        // 设置可以获取焦点
        window.setFocusable(b.fouse);
        // 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
//        window.setFocusableInTouchMode(true);
        window.setFocusable(true);

        // 更新popupwindow的状态
        window.update();
        //  以下拉的方式显示，并且可以设置显示的位置Gravity.BOTTOM  showAtLocation
//        window.showAsDropDown(Gravity.BOTTOM, 0, );
//        window.showAtLocation(BOTTOM,0,0,0);
        //        设置Popupwindow显示位置（从底部弹出）
//        window2.showAtLocation(findViewById(R.id.inposi_service_mode), Gravity.BOTTOM, 0, 0);
        window.showAtLocation(contentview, b.gravity, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //点击外部区域消失改变颜色
                setBackgroundAlpha(1.0f);
            }
        });

    }
    //拿到View，用来找到popupwindow中的控件
    public View getinitview(){
        return contentview;
    }
    //拿到window
    public PopupWindow getWindow(){
        return window;
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity)mContext).getWindow().setAttributes(lp);
    }

    /**
     * builder 类
     */
    public static class Builder{
        private int contentviewid;
        private int width;
        private int height;
        private int gravity;
        private boolean fouse;
        private boolean outsidecancel;
        private  String color;
        private int animstyle;
        public Builder(Context context){
            mContext = context;
        }
        public Builder setContentView(int contentviewid){
            this.contentviewid = contentviewid;
            return this;
        }
        public Builder setwidth(int width){
            this.width = width;
            return this;
        }
        public Builder setheight(int height){
            this.height = height;
            return this;
        }
        public Builder setcolor(String color){
            this.color = color;
            return this;
        }
        public Builder setgravity(int gravity){
            this.gravity = gravity;
            return this;
        }
        public Builder setFouse(boolean fouse){
            this.fouse = fouse;
            return this;
        }
        public Builder setOutSideCancel(boolean outsidecancel){
            this.outsidecancel = outsidecancel;
            return this;
        }
        public Builder setAnimationStyle(int animstyle){
            this.animstyle = animstyle;
            return this;
        }
        public SetPopupwindow builder(){
            return new SetPopupwindow(this);
        }
    }
}
