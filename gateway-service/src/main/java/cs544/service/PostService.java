package cs544.service;

import cs544.client.IPostServiceProxy;
import cs544.client.IVoteServiceProxy;
import cs544.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService implements IPostService {
    private final IPostServiceProxy postServiceProxy;
    private final IVoteServiceProxy voteServiceProxy;

    public PostService(@Autowired IPostServiceProxy postServiceProxy, @Autowired IVoteServiceProxy voteServiceProxy) {
        this.postServiceProxy = postServiceProxy;
        this.voteServiceProxy = voteServiceProxy;
    }

    @Override
    public List<Post> getAll() {
        return postServiceProxy.getAll();
    }

    @Override
    public Long add(Post post) {
        return postServiceProxy.add(post);
    }

    @Override
    public void update(Post post) {
        postServiceProxy.update(post);
    }

    @Override
    public Post get(Long id) {
        return postServiceProxy.get(id);
    }

    @Override
    public void delete(Long id) {
        postServiceProxy.delete(id);
        voteServiceProxy.deleteAllByPostId(id);
    }
}
