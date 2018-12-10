package text.jdyx.com.jdyx.activity;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import text.jdyx.com.jdyx.R;
import text.jdyx.com.jdyx.base.BaseActivity;
//登录界面

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_Login_Phone)
    EditText etLoginPhone;
    @BindView(R.id.cb_Login_VerCode)
    CheckBox cbLoginVerCode;
    @BindView(R.id.et_Login_VerCode)
    EditText etLoginVerCode;
    @BindView(R.id.tv_Login_Agreement)
    TextView tvLoginAgreement;
    @BindView(R.id.ll_Login_Agreement)
    LinearLayout llLoginAgreement;

    @Override
    protected void initActivityData() {

    }

    @Override
    protected void initActivityListener() {
    }

    @Override
    protected void initActivityView() {
        ButterKnife.bind(this);
        //先构造SpannableString
        SpannableString spanString = new SpannableString("注册，且表示已经同意《京典一线用户协议》");
        //再构造一个改变字体颜色的Span
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.Agreement));
        //将这个Span应用于指定范围的字体
        spanString.setSpan(span, spanString.length()-10, spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), spanString.length()-10, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置给EditText显示出来
        tvLoginAgreement.setText(spanString);
        setTitleBar("登录");
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.ll_Login_Agreement ,R.id.cb_Login})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.cb_Login:
                startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                break;
        }
    }
}
