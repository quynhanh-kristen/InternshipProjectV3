package com.internship.controller;

import com.internship.model.Post;
import com.internship.model.Vote;
import com.internship.service.IPostService;
import com.internship.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    IPostService postService;

    @GetMapping("/voted_post/{ip}")
    public ResponseEntity<?> findVoteByIp(@PathVariable(name = "ip") String ip){
        try {
            int votedPost = voteService.findVotedPostByUserIp(ip);
            return ResponseEntity.ok(votedPost);
        } catch (NullPointerException e){
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/doVote")
    public ResponseEntity<?> doVote(@RequestParam(name = "post_id") int post_id, @RequestParam(name = "user_ip") String user_ip){
        try {
            voteService.findById(user_ip);
            return ResponseEntity.ok(false);
        } catch (NoSuchElementException e) {
            Vote vote = new Vote();
            vote.setPost_id(post_id);
            vote.setUser_ip(user_ip);

            Post post = postService.findById(post_id);
            int voting = post.getTotalVote();
            post.setTotalVote(voting + 1);
            System.out.println("vote roi ne");
            return ResponseEntity.ok(true);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/unVote")
    public ResponseEntity<?> unVote(@RequestParam(name = "post_id") int post_id, @RequestParam(name = "user_ip") String user_ip){
        try {
            voteService.delete(user_ip);
            Post post = postService.findById(post_id);
            int voting = post.getTotalVote();
            post.setTotalVote(voting - 1);
            postService.savePost(post);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok(false);
        }
    }
}



