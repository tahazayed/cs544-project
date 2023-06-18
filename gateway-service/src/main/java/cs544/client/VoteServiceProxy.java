package cs544.client;

import cs544.dto.VoteCreationObject;

import cs544.model.Vote;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VoteServiceProxy implements IVoteServiceProxy {
    //private RestTemplate restTemplate;
    RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8084/api";

    @Override
    public List<Vote> getAll() {
        String getAllUrl = baseUrl + "/vote/";
        ResponseEntity<List<Vote>> response =
                restTemplate.exchange(getAllUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Vote>>() {
                        });
        return response.getBody();
    }

    @Override
    public List<Vote> getAllByPostId(@NotNull Long id) {
        String getAllUrl = baseUrl + "/vote/post/" + id.toString();
        ResponseEntity<List<Vote>> response =
                restTemplate.exchange(getAllUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Vote>>() {
                        });
        return response.getBody();
    }

    @Override
    public List<Vote> getAllByCommentId(@NotNull Long id) {
        String getAllUrl = baseUrl + "/vote/comment/" + id.toString();
        ResponseEntity<List<Vote>> response =
                restTemplate.exchange(getAllUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Vote>>() {
                        });
        return response.getBody();
    }

    @Override
    public Vote get(@NotNull Long id) {
        String getUrl = baseUrl + "/vote/" + id.toString();
        ResponseEntity<Vote> response =
                restTemplate.exchange(getUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Vote>() {
                        });
        return response.getBody();
    }
    @Override
    public Long add(VoteCreationObject voteCreationObject) {
        String postUrl = baseUrl + "/vote/";
        URI uri = restTemplate.postForLocation(postUrl, voteCreationObject);
        if (uri == null) { return null; }
        Matcher m = Pattern.compile(".*/vote/(\\d+)").matcher(uri.getPath());
        m.matches();
        return Long.parseLong(m.group(1));
    }

    @Override
    public void delete(@NotNull Long id) {
        String postUrl = baseUrl + "/vote/{id}";
        restTemplate.delete(postUrl, id);
    }

    @Override
    public void deleteAllByPostId(@NotNull Long id) {
        String postUrl = baseUrl + "/vote/post/{id}";
        restTemplate.delete(postUrl, id);
    }
    @Override
    public void deleteAllByCommentId(@NotNull Long id) {
        String postUrl = baseUrl + "/vote/comment/{id}";
        restTemplate.delete(postUrl, id);
    }
}
