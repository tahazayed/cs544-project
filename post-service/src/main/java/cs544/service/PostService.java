package cs544.service;

import cs544.model.Post;
import cs544.repository.IPostDao;
import jakarta.transaction.Transactional;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService implements IPostService {

    @Autowired
    private IPostDao iPostDao;
    public List<Post> getAll(){

        return iPostDao.findAll();
    }

    public Post add(Post post){
        post.generateDate();
        return iPostDao.save(post);
    }

    public Post update(Post post){
        post.generateDate();
        return iPostDao.save(post);
    }

    public Post get(Long id){

        return iPostDao.findById(id).get();
    }

    public void delete(Long id){

        iPostDao.deleteById(id);
    }

}
