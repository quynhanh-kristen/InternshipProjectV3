package com.internship.controller;


import com.internship.model.Post;
import com.internship.repository.PostRepository;
import com.internship.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @Autowired
    IPostService postService;

    @Autowired
    PostRepository postRepository;
    @GetMapping("/")
    public String getAllPost(Model model,
                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "sortBy", defaultValue = "new") String sortBy){
        final int PAGE_SIZE = 12;
        Page<Post> postList;
        if (sortBy.equals("old")){
            postList = postRepository.findAll(PageRequest.of(page-1, PAGE_SIZE, Sort.by("createdDate")));
        } else if (sortBy.equals("AtoZ")){
            postList = postRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by("title")));
        } else if (sortBy.equals("ZtoA")){
            postList = postRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by("title").descending()));
        } else {
            postList = postRepository.findAll(PageRequest.of(page-1, PAGE_SIZE, Sort.by("createdDate").descending()));
        }
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("postList", postList);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", postList.getTotalPages());
        return "index";
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> findByid(@PathVariable(name = "id") int id){
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

}
