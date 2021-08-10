package com.internship.service.impl;

import com.internship.model.Vote;
import com.internship.repository.VoteRepository;
import com.internship.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public void save(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public List<Integer> findVotedPostByUserIp(String ip) {
        return voteRepository.findVotedPostByUserIp(ip);
    }
}
