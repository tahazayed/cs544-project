package cs544.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Vote implements Serializable{

    @Serial
    private static final long serialVersionUID = 7654201007204127307L;

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

    private int version;

    public Vote() {

    }

}
