package com.whp.buy.utils.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;

/**
 * @author : 张吉伟
 * @data : 2018/7/14 18:17
 * @descrpition :
 */
@Component
public class ImgUtilTg {
    private static ImgUtilTg imgUtilTg;

    @Autowired
    private FastDFSUtils fastDFSUtils;

    @PostConstruct
    public void init() {
        imgUtilTg = this;
        imgUtilTg.fastDFSUtils = this.fastDFSUtils;
    }


    public String FileImg(MultipartFile file, int width, int height) {
        try {
            //FastDFSUtils fastDFSUtils=new FastDFSUtils();
            byte[] bytes = new byte[102400];
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length);
            Thumbnails.of(inputStream).size(width, height).toOutputStream(outputStream);
            bytes = outputStream.toByteArray();
            //获取源文件名称
            String originalFileName = file.getOriginalFilename();
            //获取文件后缀--.doc
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String fileName = file.getName();
            //获取文件大小
            long fileSize = bytes.length;

            System.out.println(fileName + "==" + bytes + "==" + fileSize + "==" + extension);
            return imgUtilTg.fastDFSUtils.uploadFile(bytes, fileSize, extension);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
