package cs544.service;

import cs544.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAll();

    // List<Comment>
    Long add(Comment comment);

    void update(Comment comment);

    void delete(Long id);

    Comment get(Long id);

    void deleteAllByPostId(Long postId);
}
