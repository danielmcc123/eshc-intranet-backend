package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.Task;
import com.eshc.backend.respositories.ActionPointRepository;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

    @ApiOperation(
            value ="Fetch all action points",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Action Points")})
    @GetMapping
    public Page<ActionPoint> getActionPoints(Pageable pageable) {
        return actionPointRepository.findAll(pageable);
    }


    @ApiOperation("Count all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllActionPoints() {
        return actionPointRepository.count();
    }


    @ApiOperation(
            value ="Fetch all action points",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Action Points")})
    @GetMapping("/fromlist/{ids}")
    public Page<ActionPoint> getActionPointsFromList(Set<Long> ids, Pageable pageable){
        Iterable<ActionPoint> actionPoints = actionPointRepository.findAllById(ids);
        List<ActionPoint> actionPointList = Lists.newArrayList(actionPoints);
        return new PageImpl<>(actionPointList, pageable, actionPointList.size());
    }

//    @GET
//    @Path("/changes/{id}")
//    @ApiOperation("Get changes")
//    public List<ActionPoint> getChanges(@PathParam("id") Long id){
//        return actionPointRepository.getAllChangesToAnActionpoint(id);
//    }

}
