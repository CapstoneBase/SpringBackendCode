package com.capstone.yeolmaeTeamProject.domain.community.dao;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
