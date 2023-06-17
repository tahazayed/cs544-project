package cs544;

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
        return iPostDao.save(post);
    }

    public Post update(Post post){
        return iPostDao.save(post);
    }

    public Post get(long id){
        return iPostDao.findById(id).get();
    }

    public void delete(long id){
        iPostDao.deleteById(id);
    }

}
