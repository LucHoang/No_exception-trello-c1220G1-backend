package com.example.no_exception_trello_c1220g1.service.commentService;

import com.example.no_exception_trello_c1220g1.model.dto.CommentResponse;
import com.example.no_exception_trello_c1220g1.model.entity.Comment;
import com.example.no_exception_trello_c1220g1.repository.ICommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
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
}
