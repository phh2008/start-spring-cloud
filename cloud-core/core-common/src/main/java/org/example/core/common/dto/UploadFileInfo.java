package org.example.core.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传信息
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/12
 */
@Data
public class UploadFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件原始名称
     */
    private String originalFileName;
    /**
     * 文件相对路径
     */
    private String relativePath;
    /**
     * 文件绝对路径
     */
    private String absolutePath;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 文件大小
     */
    private Long size;


}
