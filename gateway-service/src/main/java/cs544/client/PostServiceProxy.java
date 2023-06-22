package cs544.client;

import cs544.dto.PostCreationObject;
import cs544.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostServiceProxy implements IPostServiceProxy {
    private final ConfigurableEnvironment env;
    private final RestTemplate restTemplate;
    private String postUrl;
    private String pplUrl;

    @Autowired
    public PostServiceProxy(ConfigurableEnvironment env, RestTemplate restTemplate) {
        this.env = env;
        this.postUrl = env.getProperty("post.base.url") + "/post/{id}";
        this.pplUrl = env.getProperty("post.base.url") + "/post/";
        this.restTemplate = restTemplate;
    }

    @Override

    public Post get(Long id) {
        //System.out.println(postUrl);
        return restTemplate.getForObject(postUrl, Post.class, id);
    }

    @Override
    public List<Post> getAll() {
        ResponseEntity<List<Post>> response =
                restTemplate.exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Post>>() {
                        });
        return response.getBody();
    }

    @Override
    public Long add(PostCreationObject p) {
        URI uri = restTemplate.postForLocation(pplUrl, p);
        if (uri == null) {
            return null;
        }
        Matcher m = Pattern.compile(".*/post/(\\d+)").matcher(uri.getPath());
        m.matches();
        return Long.parseLong(m.group(1));
    }

    @Override
    public void update(Post p) {
        restTemplate.put(postUrl, p, p.getId());
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(postUrl, id);
    }

}
