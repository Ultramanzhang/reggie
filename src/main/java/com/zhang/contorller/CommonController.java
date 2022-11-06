package com.zhang.contorller;

import com.zhang.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件的上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basepath;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

        log.info(file.toString());

        //原始文件名
        String originalFilename = file.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，方式文件名重复造成文件覆盖
        String fileName = UUID.randomUUID().toString()+suffix;
        try {
            file.transferTo(new File(basepath+originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
