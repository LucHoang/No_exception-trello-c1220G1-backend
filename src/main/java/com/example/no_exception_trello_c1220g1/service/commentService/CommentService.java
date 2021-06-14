package com.example.no_exception_trello_c1220g1.service.commentService;

import com.example.no_exception_trello_c1220g1.model.Entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
//    @Autowired
//    private CommentRepo commentRepo;
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public List<Comment> findAll() {
//        return commentRepo.findAll();
//    }
//
//    @Override
//    public Comment findById(Long id) {
//        return commentRepo.findById(id).get();
//    }
//
//    @Override
//    public Comment save(Comment comment) {
//        comment.setAppUser(userService.getUserCurrent());
//        comment.setDate_create(new java.util.Date());
//        return commentRepo.save(comment);
//    }
//
//    @Override
//    public void delete(Long id) {
//        commentRepo.deleteById(id);
//    }
//
//    @Override
//    public List<Comment> findCommentsById(Long commentId) {
//        return commentRepo.findCommentsById(commentId);
//    }
//
//    @Override
//    public Iterable<Comment> findCommentByAppUserId(Long userId) {
//        return commentRepo.findCommentByAppUserId(userId);
//    }
//
//    @Override
//    public List<Comment> findAllByCard(Long cardId) {
//        return commentRepo.findAllByCard(cardId);
//    }
}
