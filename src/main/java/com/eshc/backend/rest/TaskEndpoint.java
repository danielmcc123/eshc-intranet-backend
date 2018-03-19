package com.eshc.backend.rest;

import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.TaskRepository;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/tasks")
@Api("Tasks")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TaskEndpoint {

    @Inject
    private TaskRepository taskRepository;

    @POST
    @ApiOperation(value = "Create a task", response = Task.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created a task"),
            @ApiResponse(code = 400, message = "Bad request")})
    public Task createTask(@ApiParam(value = "Task to be created", required = true) Task task) {
        return taskRepository.createTask(task);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Return a task given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a task"),
            @ApiResponse(code = 204, message = "Not found")})
    public Task getTask(@PathParam("id") Long id) {
        return taskRepository.getTask(id);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update a task", response = Task.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update a task"),
            @ApiResponse(code = 204, message = "Not found")})
    public Task updateTask(@PathParam("id") Long id, @ApiParam(value = "Task to be updated", required = true) Task task) {
        return taskRepository.updateTask(task);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete a note")
    @ApiResponses({@ApiResponse(code = 204, message = "Task Deleted")})
    public void deleteTask(@PathParam("id") Long id) {
        taskRepository.deleteTask(id);
    }

    @GET
    @ApiOperation("Fetch all tasks")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched tasks")})
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    @Path("/tasks")
    @ApiOperation("Count all tasks")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    public Long countAllTasks() {
        return taskRepository.countAllTasks();
    }


}
