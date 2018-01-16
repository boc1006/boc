package com.boc.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * 文件工具类
 *
 * @author Peter
 */
public class FileUtils {
    /**
     * 传入文件夹路径，该方法能够实现创建整个路径
     *
     * @param path 文件夹路径，不包含文件名称及后缀名
     */
    public static void isDir(String path) {
        String[] paths = path.split("/");
        String filePath = "";
        for (int i = 0; i < paths.length; i++) {
            if (i == 0) {
                filePath = paths[0];
            } else {
                filePath += "/" + paths[i];
            }
            creatDir(filePath);
        }
    }

    /**
     * 该方法用来判断文件夹是否存在，如果不存在则创建，存在则什么都不做
     *
     * @param filePath
     */
    public static void creatDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 获取文件后缀名（不带点）.
     *
     * @return 如："jpg" or "".
     */
    public static String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
        }
    }

    /**
     * 获取文件名称，不带后缀
     *
     * @return
     */
    public static String getFileNameNotExt(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        } else if (!fileName.contains(".")) {
            return fileName;
        } else {
            return fileName.substring(0, fileName.lastIndexOf(".")); // 不带最后的点
        }
    }
}
