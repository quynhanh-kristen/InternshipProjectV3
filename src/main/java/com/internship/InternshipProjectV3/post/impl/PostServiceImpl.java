package com.internship.InternshipProjectV3.post.impl;

import com.internship.service.IPostService;
import com.internship.utils.ImageProcessing;
import com.internship.model.Post;
import com.internship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public String saveImage(MultipartFile file) throws IOException {
        return ImageProcessing.saveImage(file);
    }

    @Autowired
    PostRepository postRepository;
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
