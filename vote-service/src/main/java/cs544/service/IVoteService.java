package cs544.service;

import cs544.model.Vote;

import java.util.List;

public interface IVoteService {
    List<Vote> getAll();

    void add(Vote vote);

    void update(Vote vote);

    void delete(Long voteId);

    Vote get(Long id);

    void deleteAllByPostId(Long postId);

    void deleteAllByCommentId(Long commentId);

    List<Vote> getAllByPostId(Long postId);

    List<Vote> getAllByCommentId(Long commentId);
}
