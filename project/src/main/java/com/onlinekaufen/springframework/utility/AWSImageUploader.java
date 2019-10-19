package com.onlinekaufen.springframework.utility;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.common.base.Throwables;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Prashant Khanal on 6/1/2019.
 */

@PropertySources({
        @PropertySource("classpath:application.properties")
})
@Component
public class AWSImageUploader {
    public static final Logger logger = LoggerFactory.getLogger(AWSImageUploader.class);
    private static final String SUFFIX = "/";
    @Value("${amazon.bucket.name}")
    static String amazonBucketName;

    @Value("${amazon.bucket.accessId}")
    static String amazonAccessId;

    @Value("${amazon.bucket.secretKey}")
    static String amazonSecretKey;

    @Value("${image.path}")
    static String imagePaths;

    public static String imageUpload(MultipartFile multiPart) {
        try {
            String packageName = "images";
            // credentials object identifying user for authentication
            // user must have AWSConnector and AmazonS3FullAccess for
            // this example to work
            AWSCredentials credentials = new BasicAWSCredentials(
                    amazonAccessId,
                    amazonSecretKey);
            // create a client connection based on credentials
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

            TransferManager tm = TransferManagerBuilder.standard()
                                         .withS3Client(s3Client)
                                         .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                                         .build();
            String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(multiPart.getOriginalFilename());
            File file = new File(imagePaths, fileName);
            Upload upload = tm.upload(amazonBucketName, fileName, file);
            return file.getName();
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return "";
        }

    }

    private static void deleteFile(String fileName) {
        AWSCredentials credentials = new BasicAWSCredentials(
                amazonAccessId,
                amazonSecretKey);
        // create a client connection based on credentials
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        s3Client.deleteObject(amazonBucketName, imagePaths + File.separator + fileName);
        // client.deleteObject(amazonBucketName, packageName + File.separator + "thumbs" + File.separator + fileName);
        logger.info("Deleted file" + fileName);
    }

    private static void createFolder(String folderName, AmazonS3 client) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(amazonBucketName,
                folderName + SUFFIX, emptyContent, metadata);
        // send request to S3 to create folder
        client.putObject(putObjectRequest);
        logger.info("Created folder.");
    }

}
