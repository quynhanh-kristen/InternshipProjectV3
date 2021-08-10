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

import java.sql.Date;
import java.util.List;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    IPostService postService;

    @GetMapping("/voted_post/{ip}")
    public ResponseEntity<?> findVoteByIp(@PathVariable(name = "ip") String ip){
        List<Integer> voteList = voteService.findVotedPostByUserIp(ip);
        return ResponseEntity.ok(voteList);
    }

    @GetMapping("/doVote")
    public ResponseEntity<?> doVote(@RequestParam(name = "post_id") int post_id, @RequestParam(name = "user_ip") String user_ip){
        try {
            Vote vote = new Vote();
            vote.setPost_id(post_id);
            vote.setUser_ip(user_ip);
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            vote.setVoted_date(date);
            voteService.save(vote);

            Post post = postService.findById(post_id);
            int voting = post.getTotalVote();
            post.setTotalVote(voting + 1);
            postService.savePost(post);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok(false);
        }
    }
}