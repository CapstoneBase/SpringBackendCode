package com.capstone.yeolmaeTeamProject.domain.community.dao;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
