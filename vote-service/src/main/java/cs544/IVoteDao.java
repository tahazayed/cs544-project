package cs544;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IVoteDao extends JpaRepository<Vote , Long> {
    Vote findFirstByUserIdAndCommentIdAndVote(Long userId, Long commentId, VoteOptions vote);

    void deleteAllByPostId(Long postId);
    void deleteAllByCommentId(Long commentId);
    List<Vote> findAllByPostId(Long postId);
    List<Vote> findAllByCommentId(Long commentId);
}