package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.respositories.ActionPointRepository;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/actionpoints")
@Api("Action Points")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ActionPointEndpoint {

    @Inject
    private ActionPointRepository actionPointRepository;

    @POST
    @ApiOperation(value = "Create an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created aan Action Point"),
            @ApiResponse(code = 400, message = "Bad request")})
    public ActionPoint createActionPoint(@ApiParam(value = "Action point to be created", required = true) ActionPoint actionPoint) {
        return actionPointRepository.createActionPoint(actionPoint);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Return an action point given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    public ActionPoint getActionPoint(@PathParam("id") Long id) {

        return actionPointRepository.getActionPoint(id);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update an action point", response = ActionPoint.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update an action point"),
            @ApiResponse(code = 204, message = "Not found")})
    public ActionPoint updateMember(@PathParam("id") Long id, @ApiParam(value = "Action point to be updated", required = true) ActionPoint actionPoint) {
        return actionPointRepository.updateActionPoint(actionPoint);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete an action point")
    @ApiResponses({@ApiResponse(code = 204, message = "Member Deleted")})
    public void deleteActionPoint(@PathParam("id") Long id) {
        actionPointRepository.deleteActionPoint(id);
    }

    @GET
    @ApiOperation("Fetch all action points")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetched Members")})
    public List<ActionPoint> getActionPoints() {
        return actionPointRepository.getActionPoints();
    }

    @GET
    @Path("/count")
    @ApiOperation("Count all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation")})
    public Long countAllActionPoints() {
        return actionPointRepository.countAllActionPoints();
    }
}
