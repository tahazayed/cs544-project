package cs544.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Long userid;
    private Date creation;

    public Post() {

    }

    public Post(String title, String description, String content, Long userid) {
        super();
        this.title = title;
        this.description = description;
        this.content = content;
        this.userid = userid;
    }

    @PrePersist
    public void generateDate() {
        Date currentDate = new Date();
        this.setCreation(currentDate);
    }

}
