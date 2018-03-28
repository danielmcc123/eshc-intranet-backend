package com.eshc.backend.rest;

import com.eshc.backend.models.Note;
import com.eshc.backend.respositories.NoteRepository;
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
@RequestMapping("/api/notes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class NoteEndpoint {

    @Autowired
    private NoteRepository noteRepository;

    @ApiOperation(value = "Create a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created a note"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @ApiOperation("Return a note given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a note"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @ApiOperation(value = "Update a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update a note"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @ApiOperation("Delete a note")
    @ApiResponses({@ApiResponse(code = 204, message = "Note Deleted")})
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable(value = "id") Long id) {
        noteRepository.deleteById(id);
    }

    @ApiOperation("Fetch all notes")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched notes")})
    @GetMapping
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @ApiOperation("Count all Notes")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllNotes() {
        return noteRepository.count();
    }
}
