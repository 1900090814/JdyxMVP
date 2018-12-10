package text.jdyx.com.jdyx.model.net;

/**
 * 创建日期：2018/4/26 0026 on 17:40
 * 描述:
 * 作者:侯宇航 Administrator
 */
public interface OnDownloadListener{
    /**
     * 下载成功
     */
    void onDownloadSuccess(String path);
    /**
     * 下载进度
     * @param progress
     */
    void onDownloading(int progress);
    /**
     * 下载失败
     */
    void onDownloadFailed();
}
