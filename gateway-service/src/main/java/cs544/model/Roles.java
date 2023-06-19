package cs544.model;

import cs544.model.enums.ERoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Roles {

    Long id;

    @Enumerated(EnumType.STRING)
    ERoles role = ERoles.USER;

}