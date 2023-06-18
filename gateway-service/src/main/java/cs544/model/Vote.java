package cs544.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "commentId", "vote"})})
public class Vote implements Serializable{

    @Serial
    @Transient
    private static final long serialVersionUID = 7654201007204127307L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private Long userId;
    @NotNull
    @Positive
    private Long commentId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteOptions vote;
    @NotNull
    @Positive
    private Long postId;

    @Version
    private int version;

    public Vote(Long userId, Long commentId, VoteOptions vote, Long postId) {
        this.userId = userId;
        this.commentId = commentId;
        this.vote = vote;
        this.postId = postId;
    }


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


    public Vote() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
