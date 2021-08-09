package com.internship.service.impl;

import com.internship.service.IPostService;
import com.internship.utils.ImageProcessing;
import com.internship.model.Post;
import com.internship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository repo;

    public PostServiceImpl(PostRepository repo) {
        this.repo = repo;
    }


    @Override
    public void savePost(Post post) {
        repo.save(post);
    }

    @Override
    public void saveImage(MultipartFile file) {
        ImageProcessing.saveImage(file);
    }
}
