package com.internship.service;

import com.internship.model.Vote;


public interface VoteService {
    public void save(Vote vote);
    public Integer findVotedPostByUserIp(String ip);
    public void delete(String id);
    public Vote findById(String user_ip);


}
