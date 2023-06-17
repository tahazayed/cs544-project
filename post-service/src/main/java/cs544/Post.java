package cs544;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    @Lob
    private String content;
    private Integer userid;
    private Date creation;

//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;

    public Post() {

    }

    public Post(String title,String description,String content,Integer userid){
        super();
        this.title = title;
        this.description = description;
        this.content = content;
        this.userid = userid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @PrePersist
    public void generateDate(){
        Date currentDate = new Date();
        this.setCreation(currentDate);
    }

}
