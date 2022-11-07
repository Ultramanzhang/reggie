package com.zhang.contorller;

import com.zhang.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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



        //创建一个目录对象
        File dir = new File(basepath);
        if (!dir.exists()){
            //目录不存在并创建
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basepath+originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    public void download(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basepath+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream servletOutputStream =response.getOutputStream();
            int len =0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes))!=-1){

                servletOutputStream.write(bytes,0,len);
                servletOutputStream.flush();
            }

            //关闭资源
            servletOutputStream.close();
            fileInputStream.close();;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
