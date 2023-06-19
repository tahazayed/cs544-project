package cs544.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username should not be blank")
    @Column(unique = true)
    private String username;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date creation_date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Select a strong password!")
    private String password;

    @Email(message = "Invalid email address")
    @Column(unique = true)
    @NotBlank
    private String email;

    // private String jwtToken;

    
    // @ManyToMany(cascade = CascadeType.MERGE)
    @ManyToMany
    List < Roles > roles;

    public User () {
        roles = new ArrayList<>();
    }

    public List < Roles > getRoles() {
        return roles;
    }

    public void setRoles(List < Roles > roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        if(this.getId() != null)
            return this.getId().toString();
        else   
            return null;
        // return this.getId().toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // DEFAULT FIELDS BEFORE SAVING INTO DATABASE
    @PrePersist
    protected void onCreate() {
        creation_date = Calendar.getInstance().getTime();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", creation_date=" + creation_date + ", password=" +
            password + ", email=" + email + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}