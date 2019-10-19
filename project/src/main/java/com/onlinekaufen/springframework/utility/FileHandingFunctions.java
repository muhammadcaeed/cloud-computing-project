package com.onlinekaufen.springframework.utility;

import com.google.common.base.Throwables;
import com.google.common.io.Files;
import net.coobird.thumbnailator.Thumbnails;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

import static java.nio.file.Files.*;

@PropertySources({
        @PropertySource("classpath:application.properties")
})
@Component
public class FileHandingFunctions {

    @Value("${image.path}")
    static String imagePaths;

    public static final Logger logger = LoggerFactory.getLogger(FileHandingFunctions.class);

    public static File createFiles(MultipartFile multipartFile, String tempFilePath) {
        File file = null;
        try {
            String ext = com.google.common.io.Files.getFileExtension(multipartFile.getOriginalFilename());
            //String thumnailPath = folderPath + File.separator + "thumbs";
            String fileName = UUID.randomUUID().toString() + "." + ext;
            file = new File(tempFilePath, fileName);
            logger.info("The absolute path is:" + file.getAbsolutePath());
            if (!file.exists()) {
                file.mkdirs();
                logger.info("The file exists.");
            } else {
                logger.info("The file doesn't exist.");
            }
            multipartFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Throwables.getStackTraceAsString(e));
        }
        logger.info("The absolute path is:" + (file != null ? file.getAbsolutePath() : ""));
        return file;
    }

    static String uploadImage(InputStream inputStream, ContentDisposition formDataContentDisposition, String packageName, String oldName) {
        String fileName = null;
        if (inputStream != null) {
            try {
                String thumbnailPath = imagePaths + packageName + File.separator + "thumbs";
                if (oldName != null) {
                    String oldFilePath = imagePaths + packageName + File.separator + oldName;
                    String oldThumbNailPath = thumbnailPath + File.separator + oldName;
                    logger.info("old file path is :" + oldFilePath);
                    logger.info("old file path is :" + oldThumbNailPath);
                    deleteIfExists(Paths.get(oldFilePath));
                    deleteIfExists(Paths.get(oldThumbNailPath));
                }
                fileName = UUID.randomUUID().toString() + "." + Files.getFileExtension(formDataContentDisposition.getFileName());
                logger.info("*********New file name is ******" + fileName);
                String totalPath = imagePaths + packageName + File.separator + fileName;
                logger.info("OLD NAME IS" + oldName);
                logger.info("*********** TOTAL PATH KO VALUE ************" + totalPath);
                createDirectories(Paths.get(imagePaths + packageName));
                long value = copy(inputStream, Paths.get(totalPath));
                logger.info("*********** NIO KO VALUE ************" + value);
                createDirectories(Paths.get(thumbnailPath));
                Thumbnails.of(new File(totalPath)).size(1000, 1000).toFile(new File(thumbnailPath + File.separator + fileName));
            } catch (IOException e) {
                logger.error(Throwables.getStackTraceAsString(e));
            }
            return fileName;
        } else {
            return null;
        }
    }

    static void deleteFile(String fileName, String packageName) {
        String totalPath = imagePaths + packageName + File.separator + fileName;
        try {
            deleteIfExists(Paths.get(totalPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}