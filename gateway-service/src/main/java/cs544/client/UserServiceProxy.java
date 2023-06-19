package cs544.client;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cs544.model.Comment;
import cs544.model.User;

@Service
public class UserServiceProxy implements IUserServiceProxy {

    private final ConfigurableEnvironment env;
    RestTemplate restTemplate = new RestTemplate();
    private String baseUrl;
    private String userUrl;
    private String pplUrl;
    private String loginUrl;
    @Autowired
    public UserServiceProxy(ConfigurableEnvironment env) {
        this.env = env;
        this.baseUrl = "http://localhost:8081/api";
        // this.baseUrl = env.getProperty("user.base.url");
        this.userUrl = baseUrl + "/user/{id}";
        this.pplUrl = baseUrl + "/user/";
        this.loginUrl = baseUrl + "/oauthtoken/";
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
    public Long register(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public User get(Long id) {
        return restTemplate.getForObject(userUrl, User.class, id);
    }

    @Override
    public User login(User user) {
        Object createdUser = restTemplate.postForObject(loginUrl, user, Object.class);

        URI uri = restTemplate.postForLocation(loginUrl, user);
        if (uri == null) {
            return null;
        }
        Matcher m = Pattern.compile(".*/post/(\\d+)").matcher(uri.getPath());
        m.matches();
        return null;
        
    }
    
}
