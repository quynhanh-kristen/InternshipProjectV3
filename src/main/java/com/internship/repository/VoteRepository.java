package com.internship.repository;

import com.internship.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v.post_id FROM Vote v where v.user_ip = ?1")
    public List<Integer> findVotedPostByUserIp(String ip);
}