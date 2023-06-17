package cs544.service;

import cs544.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceProxy implements IPostService {

    @Autowired
    private RestTemplate restTemplate;
    private final String postUrl = "http://localhost:8082/api/post/{id}";
    private final String pplUrl = "http://localhost:8082/api/post/";

    @Override
    public Post get(Long id) {
        return restTemplate.getForObject(postUrl, Post.class, id);
    }

    @Override
    public List<Post> getAll() {
        ResponseEntity<List<Post>> response =
                restTemplate.exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Post>>() {});
        return response.getBody();
    }




}
