package cs544.client;

import cs544.model.Comment;

import java.util.List;

public interface ICommentServiceProxy {
    List<Comment> getAll();

    Long add(Comment comment);

    void update(Comment comment);

    void delete(Long userId);

    Comment get(Long id);
}
