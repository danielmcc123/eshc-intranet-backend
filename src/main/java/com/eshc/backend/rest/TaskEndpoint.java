package com.eshc.backend.rest;

import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.TaskRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;

import java.util.List;
import java.util.NoSuchElementException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @ApiOperation("Count all Tasks")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllTasks() {
        return taskRepository.count();
    }


}
