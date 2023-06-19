package cs544.dto;

import cs544.model.VoteOptions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class VoteCreationObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -36048901595541303L;

    public VoteCreationObject() {
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
