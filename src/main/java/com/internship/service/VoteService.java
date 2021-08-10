package com.internship.service;

import com.internship.model.Vote;

import java.util.List;

public interface VoteService {
    public void save(Vote vote);
    public List<Integer> findVotedPostByUserIp(String ip);
}
