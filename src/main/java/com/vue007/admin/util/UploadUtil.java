package com.vue007.admin.util;

import java.io.File;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 上传处理工具类
 */
public class UploadUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    public static String uploadFile(File file, FileUpload fileUpload) {
        String newFileName = null;
        try {
            if (fileUpload == null) {
                logger.debug("文件参数不能为空！");
            } else {
                if (fileUpload.getFilePath() == null) {
                    logger.debug("文件上传路径不能为空！");
                } else {
                    String savePath = fileUpload.getFilePath();                        //获取Struts临时上传路径名
                    if (fileUpload.getFileName() == null) {
                        logger.debug("文件名称不能为空！");
                    } else {
                        String fileName = fileUpload.getFileName();                         //文件名
                        newFileName = fileName;
                        String extName = "";                                             //文件后缀名
                        boolean flag = false;
                        if (fileName.contains(".") && fileName.lastIndexOf(".") != 0) {
                            extName = fileName.substring(fileName.lastIndexOf(".") + 1);
                            for (String type : fileUpload.getAllowType()) {
                                if (type.equalsIgnoreCase(extName)) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                logger.debug("不允许上传的文件格式！");
                            } else {
                                if (file.length() <= fileUpload.getMaxSize() && file.length() >= fileUpload.getMinSize()) {
                                    // 设置文件存放目录
                                    File directory = new File(fileUpload.getFilePath());
                                    if (!directory.exists() || !directory.isDirectory()) {
                                        directory.mkdirs();
                                    }
//									newFileName = sdf.format(new Date()) + "." + extName;
                                    File newFile = new File(savePath, fileName);
                                    FileUtils.copyFile(file, newFile);
                                } else {
                                    logger.debug("文件大小不符合！");
                                }
                            }
                        } else {
                            logger.debug("文件格式有误！");
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("上传异常！！");
            e.printStackTrace();
        }
        return newFileName;
    }
}
