package cs544.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class PostCreationObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 89078901595541303L;
    
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

    public PostCreationObject() {
        
    }


}
