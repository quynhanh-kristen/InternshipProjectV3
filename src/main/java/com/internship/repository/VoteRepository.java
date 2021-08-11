package com.internship.repository;

import com.internship.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {
    @Query("SELECT v.post_id FROM Vote v where v.user_ip = ?1")
    public Integer findVotedPostByUserIp(String ip);
//    public Vote findByUserIp(String ip);
}