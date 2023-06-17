package cs544.controller;

import cs544.model.Post;
import cs544.service.PostServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {

    @Autowired
    private PostServiceProxy postService;

    @GetMapping(value = "/post/",produces = "application/json")
    public List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping(value = "/post/{id}",produces = "application/json")
    public Post get(@PathVariable long id){
        return postService.get(id);
    }



    @PostMapping(value = "/post/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Post post) {
        Long response = postService.add(post);
        post.generateDate();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/post/{id}", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable Long id) {
        post.setId(id);
        post.generateDate();
        postService.update(post);
        return new ResponseEntity<>("Updated Successful", HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable long id){
        postService.delete(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}
