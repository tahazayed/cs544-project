package cs544.controller;

import cs544.dto.CommentCreationObject;
import cs544.model.Comment;
import cs544.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    private final ICommentService commentService;
    public CommentRestController(@Autowired ICommentService CommentService) {
        this.commentService = CommentService;
    }

    @GetMapping(value= "/comment/", produces="application/json")

    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value= "/comment/{id}", produces = "application/json")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Comment comment = commentService.get(id);
        if (comment == null) {
            return  ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @PostMapping(value= "/comment/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody CommentCreationObject commentCreationObject) {
        Comment comment = new Comment();
        comment.setName(commentCreationObject.getName());
        comment.setUserId(commentCreationObject.getUserId());
        comment.setComment(commentCreationObject.getComment());
        comment.setPostId(commentCreationObject.getPostId());
        Long response = commentService.add(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value= "/comment/{id}", consumes = "application/json")
    public Comment update(@RequestBody Comment comment,@PathVariable Long id) {
        comment.setId(id);
        commentService.update(comment);
        return comment;
    }
    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable Long id) {

        commentService.delete(id);
    }

    @DeleteMapping("/comment/post/{id}")
    public void deleteAllByPostId(@PathVariable Long id) {

        commentService.deleteAllByPostId(id);
    }
}
