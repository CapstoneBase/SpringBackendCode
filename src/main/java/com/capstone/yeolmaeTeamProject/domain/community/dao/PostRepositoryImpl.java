package com.capstone.yeolmaeTeamProject.domain.community.dao;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import com.capstone.yeolmaeTeamProject.domain.community.domain.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findAllByParentCategoryAndCategory(String parentCategory, String category, Pageable pageable) {
        QPost qPost = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (parentCategory != null) builder.and(qPost.parentCategory.eq(parentCategory));
        if (category != null) builder.and(qPost.category.eq(category));

        List<Post> posts = queryFactory.selectFrom(qPost)
                .where(builder.getValue())
                .orderBy(qPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory.from(qPost)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(posts, pageable, count);
    }

}
