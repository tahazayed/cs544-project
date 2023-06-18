package cs544.client;

import cs544.model.Comment;
import cs544.model.Comment;
import org.springframework.core.ParameterizedTypeReference;
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
    RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8084/api";
    private final String commentUrl = baseUrl + "/comment/{id}";
    private final String pplUrl = baseUrl + "/comment/";

    @Override

    public Comment get(Long id) {
        return restTemplate.getForObject(commentUrl, Comment.class, id);
    }

    @Override
    public void deleteAllByPostId(Long id) {
        restTemplate.delete(baseUrl + "/comment/post/{id}", id);
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
}
