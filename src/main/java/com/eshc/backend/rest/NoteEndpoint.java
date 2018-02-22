package com.eshc.backend.rest;

import com.eshc.backend.models.Note;
import com.eshc.backend.respositories.NoteRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/notes")
public class NoteEndpoint {

    @Inject
    private NoteRepository noteRepository;

    @POST
    public Note createNote(Note note) {
        return noteRepository.createNote(note);
    }

    @GET
    @Path("/{id}")
    public Note getNote(Long id) {
        return noteRepository.getNote(id);
    }

    @PUT
    public Note updateNote(Note note) {
        return noteRepository.updateNote(note);
    }

    @DELETE
    @Path("/{id}")
    public void deleteNote(Long id) {
        noteRepository.deleteNote(id);
    }

    @GET
    public List<Note> getNotes() {
        return noteRepository.getNotes();
    }

    @Path("/count")
    @Produces(APPLICATION_JSON)
    @GET
    public Long countAllNotes() {
        return noteRepository.countAllNotes();
    }
}
