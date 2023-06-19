package cs544.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descriptions;

}
