package text.jdyx.com.jdyx.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import text.jdyx.com.jdyx.R;
import text.jdyx.com.jdyx.base.BaseActivity;
import text.jdyx.com.jdyx.fragment.InformationFragment;
import text.jdyx.com.jdyx.fragment.MessageFragment;
import text.jdyx.com.jdyx.fragment.MyFragment;
import text.jdyx.com.jdyx.fragment.VideoFragment;

//主页面
public class HomePageActivity extends BaseActivity {

    @BindView(R.id.rb_HomePage_Video)
    RadioButton rbHomePageVideo;
    @BindView(R.id.rb_HomePage_Information)
    RadioButton rbHomePageInformation;
    @BindView(R.id.iv_HomePage_Photograph)
    ImageView ivHomePagePhotograph;
    @BindView(R.id.rb_HomePage_Message)
    RadioButton rbHomePageMessage;
    @BindView(R.id.rb_HomePage_My)
    RadioButton rbHomePageMy;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.fl_HomePage)
    FrameLayout flHomePage;
    private List<Fragment> listFragment;
    private FragmentManager fm;

    @Override
    protected void initActivityData() {
        listFragment = new ArrayList<>();
        listFragment.add(new VideoFragment());
        listFragment.add(new InformationFragment());
        listFragment.add(new MessageFragment());
        listFragment.add(new MyFragment());
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fl_HomePage, listFragment.get(0));
        transaction.add(R.id.fl_HomePage, listFragment.get(1));
        transaction.add(R.id.fl_HomePage, listFragment.get(2));
        transaction.add(R.id.fl_HomePage, listFragment.get(3));
        transaction.show(listFragment.get(0)).hide(listFragment.get(1)).hide(listFragment.get(2))
                .hide(listFragment.get(3));
        transaction.commit();
    }

    @Override
    protected void initActivityListener() {
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId){
                    case R.id.rb_HomePage_Video:
                        transaction.show(listFragment.get(0)).hide(listFragment.get(1)).hide(listFragment.get(2))
                                .hide(listFragment.get(3)).commit();
                        break;
                    case R.id.rb_HomePage_Information:
                        transaction.hide(listFragment.get(0)).show(listFragment.get(1)).hide(listFragment.get(2))
                                .hide(listFragment.get(3)).commit();
                        break;
                    case R.id.rb_HomePage_Message:
                        transaction.hide(listFragment.get(0)).hide(listFragment.get(1)).show(listFragment.get(2))
                                .hide(listFragment.get(3)).commit();
                        break;
                    case R.id.rb_HomePage_My:
                        transaction.hide(listFragment.get(0)).hide(listFragment.get(1)).hide(listFragment.get(2))
                                .show(listFragment.get(3)).commit();
                        break;
                }
            }
        });
    }

    @Override
    protected void initActivityView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_home_page;
    }

    @OnClick({R.id.rb_HomePage_Video, R.id.rb_HomePage_Information, R.id.iv_HomePage_Photograph, R.id.rb_HomePage_Message, R.id.rb_HomePage_My})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_HomePage_Photograph:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
