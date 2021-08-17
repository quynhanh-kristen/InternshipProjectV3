package com.internship.controller;

import com.internship.model.Post;
import com.internship.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Date;

@Controller
public class PostController {

    private static boolean flagCheckSubmision = false;

    @Autowired
    private PostServiceImpl service;

    @RequestMapping(value = "/upload")
    public String showUploadPage(Model model) {
        Post post = new Post();
        model.addAttribute(post);
        return "uploadPage";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody String savedUploadFile(@RequestParam("file-input") MultipartFile file,
                                  @RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  HttpServletRequest request, HttpServletResponse response
                                  ) throws IOException {


            Post post = new Post(title, content, file.getContentType());
            String fileId = service.saveImage(file);
            if (fileId != null) {
                post.setCreatedDate(new Date());

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String username = auth.getName();
                post.setCreatedUser(username);

                post.setFileID(fileId);
                service.savePost(post);
            }


            System.out.println(flagCheckSubmision);

            return "redirect:/logout";

        }
}

