package com.internship.controller;

import com.internship.model.Vote;
import com.internship.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class VoteController {

    @Autowired
    VoteRepository voteRepository;

    @GetMapping("/voted_post/{ip}")
    public ResponseEntity<?> findVoteByIp(@PathVariable(name = "ip") String ip){
        List<String> voteList = voteRepository.findVotedPostByUserIp(ip);
        return ResponseEntity.ok(voteList);
    }
}