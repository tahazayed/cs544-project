package cs544.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Data
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    @Lob
    private String content;
    @NotNull
    @Positive
    private Long userId;

    private Date creation;

    @Version
    private int version;

    public Post() {

    }


    @PrePersist
    public void generateDate() {
        Date currentDate = new Date();
        this.setCreation(currentDate);
    }

}
