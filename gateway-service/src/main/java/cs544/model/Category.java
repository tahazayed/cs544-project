package cs544.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descriptions;

//    @OneToMany(mappedBy = "category")
//    private List<Post> posts;

}
