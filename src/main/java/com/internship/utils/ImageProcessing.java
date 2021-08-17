package com.internship.utils;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.ByteArrayContent;

import com.internship.model.Post;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ImageProcessing {
    private static final java.io.File SAVED_FILE = new java.io.File(System.getProperty("user.home"), "UploadImages");

    public static String saveImage(MultipartFile file){
        String fileName = null;
        OutputStream out = null;
        try {
            byte[] bytes = file.getBytes();

            //Format name's image/video
            fileName = file.getOriginalFilename();
            if(fileName != null){
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String strDate = dateFormat.format(date);

                fileName = strDate + "_" + fileName;

                int fileNameLength = fileName.length();
                if (fileNameLength > 100 ){
                    int lastIndex = fileName.lastIndexOf(".");
                    fileName = strDate + fileName.substring(lastIndex, fileNameLength);
                }
            }
            String contentType = file.getContentType();
            String idFolderParent = "1cruu-rfnAwl3e-nsOt8QywvbFnT7VQ20";

            if(!SAVED_FILE.exists()){
                SAVED_FILE.mkdirs();
            }

            java.io.File temp = new java.io.File(SAVED_FILE.getAbsolutePath() + "\\" + fileName);
            if(temp.exists()){
                System.out.println("OK");
            }
            file.transferTo(new java.io.File(SAVED_FILE.getAbsolutePath() + "\\" + fileName));

//            File f = createGoogleFile(idFolderParent, contentType, fileName, bytes);
//            fileName = f.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>FileName: " + fileName);
        return fileName;
    }

    private static File _createGoogleFile(String googleFolderIdParent, String contentType, //
                                          String customFileName, AbstractInputStreamContent uploadStreamContent) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName(customFileName);

        List<String> parents = Arrays.asList(googleFolderIdParent);
        fileMetadata.setParents(parents);
        //
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file = driveService.files().create(fileMetadata, uploadStreamContent)
                .setFields("id, webContentLink, webViewLink, parents").execute();

        return file;
    }

    // Create Google File from byte[]
    public static File createGoogleFile(String googleFolderIdParent, String contentType, //
                                        String customFileName, byte[] uploadData) throws IOException {
        //
        AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, uploadData);
        //
        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
    }

}
