package com.capstone.yeolmaeTeamProject.domain.community.dao;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom, QuerydslPredicateExecutor<Post> {

}
