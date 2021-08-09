package com.internship.service;

import com.internship.model.Post;
import org.springframework.web.multipart.MultipartFile;

public interface IPostService {
    void savePost(Post post);
    void saveImage(MultipartFile file);
}
