package com.eshc.backend.rest;

import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.TaskRepository;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


public class TaskEndpoint {

    @Inject
    private TaskRepository taskRepository;

    @POST
    public Task createTask(Task task) {
        return taskRepository.createTask(task);
    }

    @GET
    @Path("/{id")
    public Task getTask(Long id) {
        return taskRepository.getTask(id);
    }

    @PUT
    public Task updateTask(Task task) {
        return taskRepository.updateTask(task);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

    @GET
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    @Path("/tasks")
    @Produces(APPLICATION_JSON)
    public Long countAllTasks() {
        return taskRepository.countAllTasks();
    }


}
