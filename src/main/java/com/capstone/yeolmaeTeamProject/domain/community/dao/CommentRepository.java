package com.capstone.yeolmaeTeamProject.domain.community.dao;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);

    List<Comment> findAllByPostIdAndIsDeletedFalse(Long postId);
}
