package cs544;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;

public class VoteCreationObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -36048901595541303L;

    public VoteCreationObject(Long userId, Long commentId, VoteOptions vote, Long postId) {
        this.userId = userId;
        this.commentId = commentId;
        this.vote = vote;
        this.postId = postId;
    }

    public VoteCreationObject() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public VoteOptions getVote() {
        return vote;
    }

    public void setVote(VoteOptions vote) {
        this.vote = vote;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @NotNull
    @Positive
    private Long userId;
    @NotNull
    @Positive
    private Long commentId;
    @Enumerated(EnumType.STRING)
    private VoteOptions vote;
    @NotNull
    @Positive
    private Long postId;
}
