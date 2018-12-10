package text.jdyx.com.jdyx.utils;

import android.os.Environment;

/**
 * 创建日期：2018/12/8 0008 on 14:24
 * 描述:
 * 作者:侯宇航 Administrator
 */
public class Constants {
//    视频下载文件夹
    public static final String DOWN_LOAD_VIDEO = Environment.getExternalStorageDirectory() + "/DCIM/Camera/";
    //    视频下载进度
    public static final int DOWNLOAD_PROGRESS = 1014;
    //    下载失败
    public static final int DOWNLOAD_FAIL = 1015;
    //    下载成功
    public static final int DOWNLOAD_SUCCESS = 1016;
}
