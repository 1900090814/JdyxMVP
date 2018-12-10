package text.jdyx.com.jdyx.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import text.jdyx.com.jdyx.R;
import text.jdyx.com.jdyx.base.BaseFragment;

/**
 * 视频页面
 */
public class VideoFragment extends BaseFragment {


    @BindView(R.id.cb_Fr_Location)
    CheckBox cbFrLocation;
    @BindView(R.id.iv_Fr_LocationIcon)
    ImageView ivFrLocationIcon;
    @BindView(R.id.tb_Fr_Video)
    TabLayout tbFrVideo;
    @BindView(R.id.vp_Fr_Video)
    ViewPager vpFrVideo;
    Unbinder unbinder;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFragmentListener() {

    }

    @Override
    protected void initFragmentData() {

    }

    @Override
    protected void initFragmentView(View view) {

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
