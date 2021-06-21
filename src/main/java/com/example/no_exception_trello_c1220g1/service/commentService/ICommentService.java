package com.example.no_exception_trello_c1220g1.service.commentService;

import com.example.no_exception_trello_c1220g1.model.dto.CommentResponse;
import com.example.no_exception_trello_c1220g1.model.entity.Comment;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;
import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {

    List<CommentResponse> findAllByCardId(long cardId);
}
