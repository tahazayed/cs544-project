package cs544.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Comment(String name, Long userId, String comment) {
        super();
        this.name = name;
        this.userId = userId;
        this.comment = comment;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment1)) return false;
        return Objects.equals(id, comment1.id) && Objects.equals(name, comment1.name) && Objects.equals(userId, comment1.userId) && Objects.equals(comment, comment1.comment) && Objects.equals(dateTime, comment1.dateTime) && Objects.equals(postId, comment1.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, comment, dateTime, postId);
    }


}
