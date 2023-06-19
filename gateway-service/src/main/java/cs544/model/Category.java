package cs544.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class Category {

    private Long id;
    private String name;
    private String descriptions;

}
