package cs544.dto;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentCreationObject implements Serializable {

    @NotBlank
    private String name;
    @Positive
    private Long userId;
    @NotBlank
    private String comment;
    private LocalDateTime dateTime;
    @Positive
    private Long postId;
    @Version
    private int version;

    public CommentCreationObject() {

    }

}


