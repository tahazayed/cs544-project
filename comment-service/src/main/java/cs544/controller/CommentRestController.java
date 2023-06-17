package cs544.controller;

import cs544.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    @Autowired
    private cs544.service.CommentService CommentService;

    @GetMapping(value= "/comments/", produces="application/json")
//    public List<Comment> getAll() {
//        return CommentService.getAll();
//    }
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(CommentService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value= "/comments/{id}", produces = "application/json")
//    public Comment get(@PathVariable int id) {
//        return CommentService.get(id);
//    }
    public ResponseEntity<?> get(@PathVariable int id) {
        Comment comment = CommentService.get(id);
        if (comment == null) {
            return  ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @PostMapping(value= "/comments/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Comment comment) {
        Comment response = CommentService.add(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value= "/comments/{id}", consumes = "application/json")
    public Comment update(@RequestBody Comment comment,@PathVariable Integer id) {
        comment.setId(id);
        CommentService.update(comment);
        return comment;
    }


    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable int id) {
        CommentService.delete(id);
    }




}
