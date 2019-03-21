package com.eshc.backend.rest;

import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.TaskRepository;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TaskEndpoint {

    @Autowired
    private TaskRepository taskRepository;

    @ApiOperation(value = "Create a Task", response = Task.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created Task"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }

    @ApiOperation("Return a task given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a Task"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @ApiOperation(value = "Update a Task", response = Task.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated Task"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {

        return taskRepository.save(task);
    }

    @ApiOperation("Delete a Task")
    @ApiResponses({@ApiResponse(code = 204, message = "Task Deleted")})
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @ApiOperation("Fetch all tasks")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Tasks")})
    @GetMapping
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @ApiOperation("Count all Tasks")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllTasks() {
        return taskRepository.count();

    }

    @ApiOperation(
            value = "Fetch Tasks from list of ids",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Tasks")})
    @GetMapping("/fromlist/{ids}")
    public Page<Task> getAllTasksFromList(@PathVariable Set<Long> ids, Pageable pageable) {
        final PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), new Sort(
                new Sort.Order(Sort.Direction.ASC, "created")));
        Iterable<Task> notes = taskRepository.findByIdIn(ids, pageRequest);
        List<Task> taskList = Lists.newArrayList(notes);
        return new PageImpl<>(taskList, pageable, taskList.size());
    }


}
