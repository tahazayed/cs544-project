package cs544.controller;

import cs544.client.IPostServiceProxy;
import cs544.model.Post;
import cs544.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {

    private final IPostService postService;
    public PostRestController(@Autowired IPostService postService) {

        this.postService = postService;
    }

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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/post/{id}", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable Long id) {
        post.setId(id);
        postService.update(post);
        return new ResponseEntity<>("Updated Successful", HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable long id){
        postService.delete(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}
