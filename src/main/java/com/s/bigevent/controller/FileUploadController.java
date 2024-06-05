package com.s.bigevent.controller;

import com.s.bigevent.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") /** 初始化 multipartFile 对象*/ MultipartFile multipartFile) throws IOException {

        //将文件内容存到本地磁盘
        String originalFilename = multipartFile.getOriginalFilename();
        //保证文件名字唯一
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        multipartFile.transferTo(new File("E:\\文档\\IDEA\\bigEvent\\files\\" + filename));

        return Result.success("url访问地址");
    }
}
