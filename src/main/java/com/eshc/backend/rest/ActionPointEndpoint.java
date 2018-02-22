package com.eshc.backend.rest;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.respositories.ActionPointRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/actionpoints")
public class ActionPointEndpoint {

    @Inject
    private ActionPointRepository actionPointRepository;

    @POST
    public ActionPoint createActionPoint(ActionPoint actionPoint) {
        return actionPointRepository.createActionPoint(actionPoint);
    }

    @GET
    @Path("/{id}")
    public ActionPoint getActionPoint(Long id) {
        return actionPointRepository.getActionPoint(id);
    }

    @PUT
    public ActionPoint updateMember(ActionPoint actionPoint) {
        return actionPointRepository.updateActionPoint(actionPoint);
    }

    @DELETE
    @Path("/{id")
    public void deleteActionPoint(Long id) {
        actionPointRepository.deleteActionPoint(id);
    }

    @GET
    public List<ActionPoint> getActionPoints() {
        return actionPointRepository.getActionPoints();
    }

    @Path("/count")
    @Produces(APPLICATION_JSON)
    public Long countAllActionPoints() {
        return actionPointRepository.countAllActionPoints();
    }
}
