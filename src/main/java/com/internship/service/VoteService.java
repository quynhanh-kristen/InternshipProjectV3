package com.internship.service;

import com.internship.model.Post;
import com.internship.model.Vote;

import java.util.List;

public interface VoteService {
    public void save(Vote vote);
    public Integer findVotedPostByUserIp(String ip);
    public void delete(String id);
    public Vote findById(String user_ip);
}
