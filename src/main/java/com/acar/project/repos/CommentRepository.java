package com.acar.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acar.project.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
