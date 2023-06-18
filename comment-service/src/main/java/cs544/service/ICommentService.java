package cs544.service;

import cs544.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAll();

    Comment add(Comment comment);

    void update(Comment comment);

    void delete(Long userId);

    Comment get(Long id);
}
