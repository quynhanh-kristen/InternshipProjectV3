package com.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.model.Post;
import com.internship.repository.PostRepository;
import com.internship.service.IPostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IPostService postService;

    @MockBean
    private PostRepository postRepository;

    @Test
    void getAllPost() {
    }

    @Test
    void findById() throws Exception{
//        List<Post> listPost = new ArrayList<>();
//        long millis=System.currentTimeMillis();
//        Date date=new Date(millis);
//        listPost.add(new Post(1, "test 1", "test content", "123456", 0, date, date, "Long", "image"));
//        listPost.add(new Post(2, "test 2", "test content", "123456", 0, date, date, "Long", "image"));
//        listPost.add(new Post(3, "test 3", "test content", "123456", 0, date, date, "Long", "image"));
//        listPost.add(new Post(4, "test 4", "test content", "123456", 0, date, date, "Long", "image"));
//        listPost.add(new Post(5, "test 5", "test content", "123456", 0, date, date, "Long", "image"));
//        listPost.add(new Post(6, "test 6", "test content", "123456", 0, date, date, "Long", "image"));
//
//        Mockito.when(postService.findById(1)).thenReturn(listPost.get(0));
//
//        String url = "/post/1";
//        mockMvc.perform(get(url).addExpect(status().isOk()));
    }
}