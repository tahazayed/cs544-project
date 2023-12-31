package cs544.client;

import cs544.dto.PostCreationObject;
import cs544.model.Post;

import java.util.List;

public interface IPostServiceProxy
{
    public List<Post> getAll();
    public Long add(PostCreationObject post);
    public void update(Post post);
    public Post get(Long id);
    public void delete(Long id);

}
