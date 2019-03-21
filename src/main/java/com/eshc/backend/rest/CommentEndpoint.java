package com.eshc.backend.rest;

import com.eshc.backend.models.Comment;
import com.eshc.backend.respositories.CommentRepository;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comments")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class CommentEndpoint {

    @Autowired
    private CommentRepository commentRepository;

    @ApiOperation(value = "Create a comment", response = Comment.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created comment"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public Comment createComment(@Valid @RequestBody Comment comment, Principal principal) {
//        comment.setAuthor(KeycloakTokenUtil.GetUserDetails(principal).getId());
        return commentRepository.save(comment);
    }

    @ApiOperation("Return a comment given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved comment"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @ApiOperation(value = "Update a comment", response = Comment.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated comment"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @ApiOperation("Delete a comment")
    @ApiResponses({@ApiResponse(code = 204, message = "Comment Deleted")})
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable(value = "id") Long id) {
        commentRepository.deleteById(id);
    }

    @ApiOperation("Fetch all comments")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched comments")})
    @GetMapping
    public Page<Comment> getComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @ApiOperation("Count all comments")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllComment() {
        return commentRepository.count();
    }


    @ApiOperation(
            value = "Fetch comments from list of ids",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Notes")})
    @GetMapping("/fromlist/{ids}")
    public Page<Comment> getAllCommentsFromList(@PathVariable Set<Long> ids, Pageable pageable) {
        final PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), new Sort(
                new Sort.Order(Sort.Direction.ASC, "created")));
        Iterable<Comment> comments = commentRepository.findByIdIn(ids, pageRequest);
        List<Comment> commentList = Lists.newArrayList(comments);
        return new PageImpl<>(commentList, pageable, commentList.size());
    }
}
