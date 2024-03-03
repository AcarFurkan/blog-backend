package com.acar.project.repos;

import com.acar.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);

    @Query(value = "select TOP 5  id from post where user_id = :userId order by create_date desc",
            nativeQuery = true)
    List<Long> findTopByUserId(@Param("userId") Long userId);
}
