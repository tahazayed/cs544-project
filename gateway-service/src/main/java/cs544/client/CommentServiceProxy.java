package cs544.client;

import cs544.model.Comment;
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
public class CommentServiceProxy implements ICommentServiceProxy {
    private final ConfigurableEnvironment env;

    private final RestTemplate restTemplate;
    private String baseUrl;
    private String commentUrl;
    private String pplUrl;

    @Autowired
    public CommentServiceProxy(ConfigurableEnvironment env, RestTemplate restTemplate) {
        this.env = env;
        this.baseUrl = env.getProperty("comment.base.url");
        this.commentUrl = baseUrl + "/comment/{id}";
        this.pplUrl = baseUrl + "/comment/";
        this.restTemplate = restTemplate;
    }

    @Override

    public Comment get(Long id) {

        return restTemplate.getForObject(commentUrl, Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        ResponseEntity<List<Comment>> response =
                restTemplate.exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Comment>>() {
                        });
        return response.getBody();
    }

    @Override
    public Long add(Comment p) {
        URI uri = restTemplate.postForLocation(pplUrl, p);
        if (uri == null) {
            return null;
        }
        Matcher m = Pattern.compile(".*/comment/(\\d+)").matcher(uri.getPath());
        m.matches();
        return Long.parseLong(m.group(1));
    }

    @Override
    public void update(Comment p) {

        restTemplate.put(commentUrl, p, p.getId());
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(commentUrl, id);
    }

    @Override
    public void deleteAllByPostId(Long id) {

        restTemplate.delete(baseUrl + "/comment/post/{id}", id);
    }
}
