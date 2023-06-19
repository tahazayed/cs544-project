package cs544.model;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private Long userId;
    private Date creation;

    @Version
    private int version;

    public Post() {

    }
}
