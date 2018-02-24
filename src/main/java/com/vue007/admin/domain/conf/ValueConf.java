package com.vue007.admin.domain.conf;

/**
 * 公共静态常量配置
 *
 * @author Xiangyang
 * @date 2017-09-04 15:19
 */
public class ValueConf {

    //APK文件上传的最大文件大小
    public final static long APK_MAX_UPLOAD_SIZE = 26214400L;

    //用户session存储键名
    public final static String SESSION_USER_KEY = "user";

    public final static String SESSION_USER_ID = "user_id";

    public final static String TOKEN = "x-token";

    public final static String DEFAULT_USER_HEAD_IMG = "https://3d-community.s3-ap-southeast-1.amazonaws.com/file/20171108/e2bd539d33864860a769c78bf614a77c.jpg";

    public final static String DEFAULT_USER_NICKNAME = "默认用户名";

}
