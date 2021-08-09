package com.internship.utils;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;

import com.internship.utils.GoogleDriveUtils;

import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ImageProcessing {
    public static void saveImage(MultipartFile file){

        try {
            byte[] bytes = file.getBytes();

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String strDate = dateFormat.format(date);

            String fileName = strDate + "_" + file.getOriginalFilename();
            String contentType = file.getContentType();
            String idFolderParent = "1cruu-rfnAwl3e-nsOt8QywvbFnT7VQ20";

            File f = createGoogleFile(idFolderParent, contentType, fileName, bytes);
            System.out.println(f.getId());
//            Path path = Paths.get(folder + file.getOriginalFilename());
//            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
