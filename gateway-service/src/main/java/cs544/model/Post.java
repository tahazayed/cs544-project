package cs544.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class Post {

    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
    private Long userId;
    private Date creation;

    public Post() {

    }
}
