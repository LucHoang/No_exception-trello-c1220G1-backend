package com.example.no_exception_trello_c1220g1.service.commentService;

import com.example.no_exception_trello_c1220g1.model.dto.CommentResponse;
import com.example.no_exception_trello_c1220g1.model.entity.Comment;
import com.example.no_exception_trello_c1220g1.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository commentRepository;

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
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {

    }

    public List<CommentResponse> findAllByCardId(long cardId) {
        List<Object> resultSet = commentRepository.findAllByCardId(cardId);

        List<CommentResponse> comments = new ArrayList<>();
        for (Object o :resultSet) {
            CommentResponse comment = new CommentResponse();
            comment.setUsername(((Object[])o)[0].toString());
            comment.setAvatar(((Object[])o)[1].toString());
            comment.setContent(((Object[])o)[2].toString());
            comment.setCreated_date(((Object[])o)[3].toString());
            comments.add(comment);
        }
        return comments;
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
