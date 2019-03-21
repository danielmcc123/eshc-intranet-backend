package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.Comment;
import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.ActionPointRepository;
import com.eshc.backend.respositories.CommentRepository;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/actionpoints")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ActionPointEndpoint {

    @Autowired
    private ActionPointRepository actionPointRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CommentEndpoint commentEndpoint;

    @Autowired
    private TaskEndpoint taskEndpoint;

    @ApiOperation(value = "Create an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created an ActionPoint"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public ActionPoint createActionPoint(@Valid @RequestBody ActionPoint actionPoint, Principal principal) {
//        actionPoint.setLeadContributor(KeycloakTokenUtil.GetUserDetails(principal).getId());
        return actionPointRepository.save(actionPoint);
    }

    @ApiOperation("Return an action point given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public ActionPoint getActionPoint(@PathVariable Long id) {
        return actionPointRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @ApiOperation(value = "Update an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    @Transactional
    public ActionPoint updateActionPoint(@PathVariable Long id, @Valid @RequestBody ActionPoint actionPoint) {
        ActionPoint before = getActionPoint(id);
        if(before.equals(actionPoint)) return actionPoint;
        if (before.getVersion().equals(actionPoint.getVersion())) {
            return actionPointRepository.save(actionPoint);
        } else {
            throw new OutdatedEntityVersionException();
        }
    }

    @ApiOperation(value = "Add a task", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task added to Actionpoint"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/addtaskto/{id}")
    @Transactional
    public ActionPoint addTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        ActionPoint actionPoint = getActionPoint(id);
        actionPoint.getTasks().add(task.getId());
        return actionPointRepository.save(actionPoint);
    }

    @ApiOperation(value = "Add a comment", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task added to Actionpoint"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/addcommentto/{id}")
    @Transactional
    public ActionPoint addNote(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        ActionPoint actionPoint = getActionPoint(id);
        Comment savedComment = commentRepository.save(comment);
        actionPoint.getListOfComments().add(savedComment.getId());
        return actionPointRepository.save(actionPoint);
    }

    @ApiOperation("Delete an action point")
    @ApiResponses({@ApiResponse(code = 204, message = "ActionPoint Deleted")})
    @DeleteMapping("/{id}")
    public void deleteActionPoint(@PathVariable(value = "id") Long id) {
        try {
            actionPointRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoSuchElementException("No such Comment with id " + id);
        }
    }

    @ApiOperation(
            value = "Fetch all Actionpoints",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Action Points")})
    @GetMapping
    public Page<ActionPoint> getActionPoints(Pageable pageable) {
        return actionPointRepository.findAll(pageable);
    }


    @ApiOperation("Count all Actionpoints")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllActionPoints() {
        return actionPointRepository.count();
    }


    @ApiOperation(
            value = "Fetch Actionpoints from list of ids",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Action Points")})
    @GetMapping("/fromlist/{ids}")
    public Page<ActionPoint> getActionPointsFromList(@PathVariable Set<Long> ids, Pageable pageable) {
        final PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), new Sort(
                new Sort.Order(Sort.Direction.DESC, "updated")
        ));
        Iterable<ActionPoint> actionPoints = actionPointRepository.findByIdIn(ids, pageRequest);
        List<ActionPoint> actionPointList = Lists.newArrayList(actionPoints);
        return new PageImpl<>(actionPointList, pageable, actionPointList.size());
    }

    @ApiOperation("Get all comments for an Action Point")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page no", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Size of each page", dataType = "string", paramType = "query"),
    })
    @GetMapping("/{id}/comments")
    public Page<Comment> getCommentsFromActionPoint(@PathVariable Long id, Pageable pageable) {
        ActionPoint actionPoint = getActionPoint(id);
        return commentEndpoint.getAllCommentsFromList(actionPoint.getListOfComments(), pageable);
    }

    @ApiOperation("Get all Tasks for an Action Point")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page no", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Size of each page", dataType = "string", paramType = "query"),
    })
    @GetMapping("/{id}/tasks")
    public Page<Task> getTasksFromActionPoint(@PathVariable Long id, Pageable pageable) {
        ActionPoint actionPoint = getActionPoint(id);
        return taskEndpoint.getAllTasksFromList(actionPoint.getTasks(), pageable);
    }

    @ApiOperation("Add an Comment to a Action Point")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully added Comment to Action Point"),
            @ApiResponse(code = 204, message = "Not found")})
    @PostMapping("/{actionId}/comment/{commentId}")
    public ActionPoint addNoteToActionPoint(@PathVariable Long actionId, @PathVariable Long commentId) {
        ActionPoint actionPoint = getActionPoint(actionId);
        Comment comment = commentEndpoint.getComment(commentId);
        actionPoint.getListOfComments().add(comment.getId());
        actionPointRepository.save(actionPoint);
        return actionPoint;
    }

    @ApiOperation("Add an Task to a Action Point")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully added Task to Action Point"),
            @ApiResponse(code = 204, message = "Not found")})
    @PostMapping("/{actionId}/task/{taskId}")
    public ActionPoint addTaskToActionPoint(@PathVariable Long actionId, @PathVariable Long taskId) {
        ActionPoint actionPoint = getActionPoint(actionId);
        Task task = taskEndpoint.getTask(taskId);
        actionPoint.getTasks().add(task.getId());
        actionPointRepository.save(actionPoint);
        return actionPoint;
    }

//    @GET
//    @Path("/changes/{id}")
//    @ApiOperation("Get changes")
//    public List<ActionPoint> getChanges(@PathParam("id") Long id){
//        return actionPointRepository.getAllChangesToAnActionpoint(id);
//    }

}
