package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.exception.SystemException;
import com.chat.wesay.pojo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
import java.io.File;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Value("${upload.directory}") // 读取配置文件中的 upload.directory 属性
    private String uploadDirectory;

    @PostMapping("/avatar")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 生成UUID作为文件名
                String uuid = UUID.randomUUID().toString();
                // 获取文件的字节数组并保存到指定路径
                byte[] bytes = file.getBytes();
                String extension = getFileExtension(file.getOriginalFilename());
                String newName=uuid+"."+extension;
                String filePath = uploadDirectory + newName;
                File saveFile = new File(filePath);
                file.transferTo(saveFile);

                // 返回UUID作为文件名
                return Result.Success(Code.SAVE_OK, newName);
            } catch (Exception e) {
                throw new SystemException();
            }
        } else {
            return Result.Error(Code.SAVE_ERR, "文件为空");
        }
    }

    // 获取文件扩展名
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
