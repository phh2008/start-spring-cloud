package org.example.core.boot.handler;

import org.example.core.boot.support.UploadSupport;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.dto.UploadFileInfo;
import org.example.core.common.jwt.IJwtInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 控制器基类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${start.uploadFolder:/home/data/upload}")
    private String uploadFolder;

    /**
     * 获取登录用户信息
     *
     * @return null if not login
     */
    public IJwtInfo getSubject() {
        return UserContextHandler.getJwtInfo();
    }

    /**
     * 获取登录用户信息
     *
     * @return CloudException if not login
     */
    public IJwtInfo getSubjectThrow() {
        return UserContextHandler.getJwtInfoThrow();
    }

    public UploadFileInfo uploadFile(MultipartFile file, String bizFolder) {
        return UploadSupport.uploadFile(file, uploadFolder, bizFolder);
    }

    public List<UploadFileInfo> uploadFile(List<MultipartFile> files, String bizFolder) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }
        return files.stream()
                .map(f -> UploadSupport.uploadFile(f, uploadFolder, bizFolder))
                .collect(Collectors.toList());
    }

}
