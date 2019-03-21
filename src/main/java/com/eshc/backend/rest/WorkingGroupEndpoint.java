package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.WorkingGroup;
import com.eshc.backend.respositories.WorkingGroupRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.NoSuchElementException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("api/working")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class WorkingGroupEndpoint {

    @Autowired
    private WorkingGroupRepository workingGroupRepository;

    @Autowired
    private ActionPointEndpoint actionPointEndpoint;

    @ApiOperation(value = "Create a Working Group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created Working Group"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public WorkingGroup createWorkingGroup(@Valid @RequestBody WorkingGroup workingGroup) {
        return workingGroupRepository.save(workingGroup);
    }


    @ApiOperation("Return a Working Group given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Working Group"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public WorkingGroup getWorkingGroup(@PathVariable Long id) {
        return workingGroupRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


    @ApiOperation("Update a working Group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated Working Group"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    @Transactional
    public WorkingGroup updateWorkingGroup(@PathVariable Long id, @Valid @RequestBody WorkingGroup workingGroup) {
        return workingGroupRepository.save(workingGroup);
    }


    @ApiOperation("Add an Action Point to a Working Group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully added ActionPoint to Working Group"),
            @ApiResponse(code = 204, message = "Not found")})
    @PostMapping("/{workingId}/{actionId}")
    public ActionPoint addActionPoint(@PathVariable Long workingId, @PathVariable Long actionId) {
        WorkingGroup workingGroup = getWorkingGroup(workingId);
        ActionPoint actionPoint = actionPointEndpoint.getActionPoint(actionId);
        workingGroup.getActionPoints().add(actionPoint.getId());
        workingGroupRepository.save(workingGroup);
        return actionPoint;
    }


    @ApiOperation("Delete a Working Group given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully deleted a Working Group"),
            @ApiResponse(code = 204, message = "Not found")})
    @DeleteMapping("/{id}")
    public void deleteWorkingGroup(@PathVariable Long id) {
        workingGroupRepository.deleteById(id);
    }


    @ApiOperation("Get all Action Points for a working group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page no", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Size of each page", dataType = "string", paramType = "query"),
    })
    @GetMapping("/{id}/actions")
    public Page<ActionPoint> getActionPoints(@PathVariable Long id, Pageable pageable) {
        WorkingGroup workingGroup = getWorkingGroup(id);
        return actionPointEndpoint.getActionPointsFromList(workingGroup.getActionPoints(), pageable);
    }


    @ApiOperation("Get all Working Groups in the system")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Working Groups"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping
    public Page<WorkingGroup> getAllWorkingGroups(Pageable pageable){
            return workingGroupRepository.findAll(pageable);
    }

}
