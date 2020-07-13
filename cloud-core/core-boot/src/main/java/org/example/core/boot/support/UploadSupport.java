package org.example.core.boot.support;

import lombok.extern.slf4j.Slf4j;
import org.example.core.common.dto.UploadFileInfo;
import org.example.core.common.exception.CloudException;
import org.example.core.common.result.ResultCodeEnum;
import org.example.core.tool.utils.DateTimeUtils;
import org.example.core.tool.utils.FileUtils;
import org.example.core.tool.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

/**
 * 文件上传
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/12
 */
@Slf4j
public class UploadSupport {

    private FileSystem fs = FileSystems.getDefault();

    /**
     * 上传文件
     * 最终保存的目录是：
     * 根目录/业务目录/文件类型/yyyyMMdd/filename.ext
     * 示例：/home/data/upload/erp/user/image/20200712/aef3f92ja32oaf32234fa0l32n.jpg
     *
     * @param file       文件
     * @param rootFolder 文件根目录（示例：/home/data/upload）
     * @param bizFolder  业务目录（示例：/erp/user）
     * @return
     */
    public static UploadFileInfo uploadFile(MultipartFile file, String rootFolder, String bizFolder) {
        if (file == null) {
            return null;
        }
        bizFolder = StringUtils.isEmpty(bizFolder) ? "/mod" : bizFolder;
        String ext = FileUtils.getExt(file.getOriginalFilename());
        StringBuilder subFolderBuilder = new StringBuilder();
        //子目录格式：/xxx/image/yyyyMMdd
        subFolderBuilder.append(bizFolder)
                .append(File.separator)
                .append(FileUtils.getTypeFolder(ext))
                .append(File.separator)
                .append(DateTimeUtils.FMT_YYYYMMDD.format(LocalDate.now()));
        //文件名：filename.ext
        String filename = StringUtils.randomUUID() + ext;
        return uploadFile(file, rootFolder, subFolderBuilder.toString(), filename);
    }

    protected static UploadFileInfo uploadFile(MultipartFile file, String rootFolder, String subFolder, String fileName) {
        try {
            Path path = Paths.get(rootFolder, subFolder, fileName);
            Path parentPath = path.getParent();
            synchronized (UploadSupport.class) {
                if (!Files.exists(parentPath)) {
                    Files.createDirectories(parentPath);
                }
            }
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            UploadFileInfo info = new UploadFileInfo();
            info.setContentType(file.getContentType());
            info.setSize(file.getSize());
            info.setFileName(fileName);
            info.setOriginalFileName(file.getOriginalFilename());
            info.setAbsolutePath(path.toFile().getPath());
            info.setRelativePath(info.getAbsolutePath().substring(info.getAbsolutePath().indexOf(new File(subFolder).getPath())));
            return info;
        } catch (Exception e) {
            log.error("上传文件出错", e);
            throw new CloudException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }

}
