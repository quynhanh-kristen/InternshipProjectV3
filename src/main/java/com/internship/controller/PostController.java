package com.internship.controller;

import com.internship.model.Post;
import com.internship.InternshipProjectV3.post.impl.PostServiceImpl;
import com.internship.utils.ImageProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class PostController {


    @Autowired
    private PostServiceImpl service;

    @RequestMapping(value = "/upload")
    public String showUploadPage(Model model) {
        Post post = new Post();
        model.addAttribute(post);
        return "uploadPage";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savedUploadFile(@RequestParam("file-input") MultipartFile file,
                                  @RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  HttpServletRequest request, HttpServletResponse response
                                  ) throws IOException {

//            ServletContext context = request.getServletContext();
            Post post = new Post(title, content, file.getContentType());
            String fileId = service.saveImage(file);
            if (fileId != null) {
                post.setCreatedDate(new Date());

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String username = auth.getName();
                post.setCreatedUser(username);

                post.setFileID(fileId);
                service.savePost(post);

//                String path = context.getRealPath("/") + "WEB-INF\\classes\\static\\uploadFiles";
//                ImageProcessing.saveImageToLoad(path);
            }

            return "redirect:/logout";

        }
}

