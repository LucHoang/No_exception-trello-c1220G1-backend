package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.tools.JavaCompiler;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
