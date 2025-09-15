package com.euansu.controller;

import com.euansu.pojo.Result;
import com.euansu.utils.QiniuUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        // 拼接存储名称
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 把文件的内容存储到本地磁盘
//        file.transferTo(new File("C:\\Code\\Java\\JavaStudy\\SpringBootStudy\\upload\\" + filename));
        System.out.println(file);
        String url = QiniuUtil.uploadFiles(file.getInputStream(),filename);
        return Result.success(url);
    }
}
