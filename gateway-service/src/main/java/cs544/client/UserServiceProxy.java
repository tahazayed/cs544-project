package cs544.client;

import java.net.URI;
// import java.net.http.HttpHeaders;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cs544.dto.UserLoginObject;
import cs544.dto.UserRegisterObject;
import cs544.model.Comment;
import cs544.model.Roles;
import cs544.model.User;
import cs544.model.enums.ERoles;

@Service
public class UserServiceProxy implements IUserServiceProxy {
    private final ConfigurableEnvironment env;
    private final RestTemplate restTemplate;
    private String baseUrl;
    private String userUrl;
    private String pplUrl;
    private String loginUrl;

    @Autowired
    public UserServiceProxy(ConfigurableEnvironment env, RestTemplate restTemplate) {
        this.env = env;
        //this.baseUrl = "http://localhost:8081/api";
        this.baseUrl = env.getProperty("user.base.url");
        this.userUrl = baseUrl + "/user/{id}";
        this.pplUrl = baseUrl + "/user/";
        this.loginUrl = baseUrl + "/oauthtoken/";
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAll() {
        ResponseEntity<List<User>> response =
                restTemplate.exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return response.getBody();
    }

    @Override
    public User register(UserRegisterObject user) {

        // ADDING DEFAULT USER ROLE EACH TIME
        Roles defaultUserRole = new Roles();
        defaultUserRole.setRole(ERoles.USER);
        List<Roles> roles = user.getRoles();
        roles.add(defaultUserRole);

        User createdUser = restTemplate.postForObject(pplUrl, user, User.class);
        return createdUser;
    }

    @Override
    public User get(Long id) {
        return restTemplate.getForObject(userUrl, User.class, id);
    }

    @Override
    public String login(UserLoginObject myUser) {
        String token = restTemplate.postForObject(loginUrl, myUser, String.class);

        // Matcher m = Pattern.compile(".*/post/(\\d+)").matcher(uri.getPath());
        // m.matches();
        return token;

    }

}
