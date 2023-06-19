package cs544.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {


    private Long id;
    @NotBlank
    private String name;

    private Long userId;
    @NotBlank
    private String comment;

    private LocalDateTime dateTime;

    private Long postId;

    public Comment() {

    }

}
