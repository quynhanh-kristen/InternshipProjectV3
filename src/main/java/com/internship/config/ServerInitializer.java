package com.internship.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ServerInitializer implements ApplicationRunner {
    @Autowired
    ServletContext context;
    private static final java.io.File SAVED_FILE = new java.io.File(System.getProperty("user.home"), "UploadImages");
    private final String LOCAL_FOLDER = SAVED_FILE.getAbsolutePath() + "\\";
    @Override
    public void run(ApplicationArguments args) throws Exception {

        String path = context.getRealPath("/") + "WEB-INF\\classes\\static\\uploadFiles";

        File uploadFolder = new File(path);

        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }

        //list files in local
        String contents[] = SAVED_FILE.list();
        for (String content: contents) {
            byte[] bytes = Files.readAllBytes(Paths.get(LOCAL_FOLDER + content));
            String dest = path + "\\" + content;
            FileOutputStream fout = new FileOutputStream(dest);
            fout.write(bytes);
        }
    }
}
