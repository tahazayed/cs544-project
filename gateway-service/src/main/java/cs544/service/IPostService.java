package cs544.service;

import cs544.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService
{
    public List<Post> getAll();
    public Long add(Post post);
    public void update(Post post);
    public Post get(Long id);
    public void delete(Long id);

}
