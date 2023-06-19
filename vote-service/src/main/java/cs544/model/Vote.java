package cs544.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "commentId", "vote"})})
@Data
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

    public Vote() {

    }

}
