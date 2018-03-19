package com.eshc.backend.rest;

import com.eshc.backend.models.Note;
import com.eshc.backend.respositories.NoteRepository;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/notes")
@Api("Notes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class NoteEndpoint {

    @Inject
    private NoteRepository noteRepository;

    @POST
    @ApiOperation(value = "Create a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created a note"),
            @ApiResponse(code = 400, message = "Bad request")})
    public Note createNote(@ApiParam(value = "Note to be created", required = true) Note note) {
        return noteRepository.createNote(note);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Return a note given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a note"),
            @ApiResponse(code = 204, message = "Not found")})
    public Note getNote(@PathParam("id") Long id) {
        return noteRepository.getNote(id);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update a note"),
            @ApiResponse(code = 204, message = "Not found")})
    public Note updateNote(@PathParam("id") Long id, @ApiParam(value = "Action point to be updated", required = true)Note note) {
        return noteRepository.updateNote(note);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete a note")
    @ApiResponses({@ApiResponse(code = 204, message = "Note Deleted")})
    public void deleteNote(@PathParam("id") Long id) {
        noteRepository.deleteNote(id);
    }

    @GET
    @ApiOperation("Fetch all notes")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched notes")})
    public List<Note> getNotes() {
        return noteRepository.getNotes();
    }

    @GET
    @Path("/count")
    @ApiOperation("Count all Notes")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    public Long countAllNotes() {
        return noteRepository.countAllNotes();
    }
}
