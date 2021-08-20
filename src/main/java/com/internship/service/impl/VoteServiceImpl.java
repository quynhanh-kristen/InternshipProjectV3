package com.internship.service.impl;

import com.internship.model.Vote;
import com.internship.repository.VoteRepository;
import com.internship.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public void save(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public Integer findVotedPostByUserIp(String ip) {
        return voteRepository.findVotedPostByUserIp(ip);
    }

    @Override
    public void delete(String user_ip) {
        Vote vote = voteRepository.findById(user_ip).get();
        voteRepository.delete(vote);
    }

    @Override
    public Vote findById(String user_ip) {
        return voteRepository.findById(user_ip).get();
    }
}
