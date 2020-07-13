package org.example.core.tool.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/12
 */
public class FileUtils {

    private static Map<String, String> FOLDER_EXT_MAP = new HashMap<>();
    private static final String DEFAULT_TYPE_FOLDER = "file";

    static {
        FOLDER_EXT_MAP.put("image", ".gif,.jpg,.jpeg,.png,.bmp,.JPG,.JPEG,.PNG,ico,icon");
        FOLDER_EXT_MAP.put("media", ".swf,.flv,.mp3,.mp4,.wav,.wma,.wmv,.mid,.avi,.mpg,.asf,.rm,.rmvb");
        FOLDER_EXT_MAP.put("doc", ".doc,.docx,.xls,.xlsx,.ppt,.pptx,.htm,.html,.txt,.md,.pdf");
        FOLDER_EXT_MAP.put("zip", ".zip,.rar,.gz,.bz2");
    }

    /**
     * 通过文件扩展名获取类型目录
     *
     * @param ext 文件扩展名 (示例：.jpg)
     * @return type folder
     */
    public static String getTypeFolder(String ext) {
        if (ext == null || "".equals(ext)) {
            return DEFAULT_TYPE_FOLDER;
        }
        return FOLDER_EXT_MAP.keySet().stream()
                .filter(key -> FOLDER_EXT_MAP.get(key).contains(ext))
                .findFirst()
                .orElse(DEFAULT_TYPE_FOLDER);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return ext or ""
     */
    public static String getExt(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            return "";
        }
        int idx = fileName.lastIndexOf(".");
        if (idx == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
