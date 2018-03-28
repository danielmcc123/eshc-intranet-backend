package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.ActionPointRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.NoSuchElementException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api/actionpoints")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ActionPointEndpoint {

    @Autowired
    private ActionPointRepository actionPointRepository;

    @Autowired
    private EntityManager entityManager;

    @ApiOperation(value = "Create an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created aan Action Point"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public ActionPoint createActionPoint(@Valid @RequestBody ActionPoint actionPoint) {
        return actionPointRepository.save(actionPoint);
    }

    @ApiOperation("Return an action point given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public ActionPoint getActionPoint(@PathVariable Long id) {
        return actionPointRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @ApiOperation(value = "Update an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    @Transactional
    public ActionPoint updateActionPoint(@PathVariable Long id, @Valid @RequestBody ActionPoint actionPoint) {
        ActionPoint before = getActionPoint(id);
        if(before.getVersion().equals(actionPoint.getVersion())){
        return actionPointRepository.save(actionPoint);
        }else{
            throw new OutdatedEntityVersionException();
        }
    }

    @ApiOperation(value = "Add a task", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task added to Actionpoint"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/addtaskto/{id}")
    @Transactional
    public ActionPoint addTask(@PathVariable Long id, @Valid @RequestBody Task task){
        ActionPoint actionPoint = getActionPoint(id);
        actionPoint.getTasks().add(task);
        return actionPointRepository.save(actionPoint);
    }

    @ApiOperation("Delete an action point")
    @ApiResponses({@ApiResponse(code = 204, message = "Member Deleted")})
    @DeleteMapping("/{id}")
    public void deleteActionPoint(@PathVariable(value = "id") Long id) {
        actionPointRepository.deleteById(id);
    }

    @ApiOperation("Fetch all action points")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetched Members")})
    @GetMapping
    public List<ActionPoint> getActionPoints() {
        return actionPointRepository.findAll();
    }

    @ApiOperation("Count all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllActionPoints() {
        return actionPointRepository.count();
    }

//    @GET
//    @Path("/changes/{id}")
//    @ApiOperation("Get changes")
//    public List<ActionPoint> getChanges(@PathParam("id") Long id){
//        return actionPointRepository.getAllChangesToAnActionpoint(id);
//    }

}
