package text.jdyx.com.jdyx.loader;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 创建日期：2018/7/2 0002 on 10:55
 * 描述:热更新
 * 作者:侯宇航 Administrator
 */
public class BuglyApplication extends TinkerApplication {
    public BuglyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "text.jdyx.com.jdyx.loader.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
