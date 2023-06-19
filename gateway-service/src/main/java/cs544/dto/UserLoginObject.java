package cs544.dto;

import java.io.Serial;
import java.io.Serializable;

import cs544.model.VoteOptions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserLoginObject implements Serializable {

    @NotBlank
    String username;
    @NotBlank
    String password;
    
     public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
