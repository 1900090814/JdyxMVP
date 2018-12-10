package text.jdyx.com.jdyx.model.biz;


import text.jdyx.com.jdyx.model.net.HttpFactroy;
import text.jdyx.com.jdyx.model.net.IHttp;

/**
 * 创建日期：2018/4/26 0026 on 13:51
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface BaseModel {
   public static IHttp iHttp= HttpFactroy.create();
}
