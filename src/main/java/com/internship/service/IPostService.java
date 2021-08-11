package com.internship.service;

import com.internship.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {
    void savePost(Post post);
    String saveImage(MultipartFile file);
    public List<Post> findAll();
    public Post findById(int id);
    public List<Post> findByTitle(String title);
}
