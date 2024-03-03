package com.acar.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acar.project.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);

    @Query(value = "select TOP 5 'commented on', c.post_id, u.user_name from "
            + "comment c left join app_user u on u.id = c.user_id "
            + "where c.post_id in :postIds", nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
