package com.whp.buy.utils.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUtil {


    public static String path(MultipartFile file, String pathname, String floderName) {

        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String ext = (FilenameUtils.getExtension(originalFilename)).toLowerCase();
            String newFileName = floderName + "." + ext;
            try {
                File fdir = new File(pathname);
                if (!fdir.exists()) {
                    fdir.mkdirs();
                }
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fdir, newFileName));
                return pathname + "/" + newFileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
