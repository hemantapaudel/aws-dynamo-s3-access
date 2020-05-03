package com.springboot.aws.example.awsdynamos3access.controller;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class S3Controller {

    @Autowired
    private AmazonS3 s3Client;

    @RequestMapping("/images")
    public String getEmployees() {

        StringBuilder builder = new StringBuilder("<!DOCTYPE html>").append("<html>").append("<body>");
        ListObjectsV2Result result = s3Client.listObjectsV2("hemantap");
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
            String url = "https://hemantap.s3.ap-south-1.amazonaws.com/" + os.getKey();
            builder.append("<br>");
            builder.append("<br>");
            builder.append("<img src=\"" + url + "\" alt=\"Smiley face\" width=\"500\" height=\"600\">");

        }
        builder.append("</body>").append("</html>");
        return builder.toString();
    }


    @RequestMapping("/uploadpicturs")
    public void uploadPictures() {
        String bucketName = "hemanta-paudel-datastore";
        String filePath = "/Users/hemantapaudel/Desktop/pictures";
        String[] list = new File(filePath).list();
        for (String file_complete_path : list) {
            String fileFullPath = filePath + "/" + file_complete_path;
            String key_name = Paths.get(fileFullPath).getFileName().toString();
            System.out.println("keyname : " + key_name + " file full path = " + fileFullPath);
            try {
                s3Client.putObject(bucketName, key_name, new File(fileFullPath));
            } catch (AmazonServiceException e) {
                System.err.println(e.getErrorMessage());
            }
        }
    }

    public static void main(String[] args) {




    }

}
