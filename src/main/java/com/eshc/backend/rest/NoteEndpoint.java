package com.eshc.backend.rest;

import com.eshc.backend.models.Note;
import com.eshc.backend.respositories.NoteRepository;
import com.eshc.backend.utils.security.KeycloakTokenUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class NoteEndpoint {

    @Autowired
    private NoteRepository noteRepository;

    @ApiOperation(value = "Create a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created note"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public Note createNote(@Valid @RequestBody Note note, Principal principal) {
        note.setAuthor(KeycloakTokenUtil.GetUserDetails(principal).getId());
        return noteRepository.save(note);
    }

    @ApiOperation("Return a note given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved note"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @ApiOperation(value = "Update a note", response = Note.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated note"),
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
    public Page<Note> getNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @ApiOperation("Count all Notes")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllNotes() {
        return noteRepository.count();
    }


    @ApiOperation(
            value = "Fetch Notes from list of ids",
            notes = "Pagination available at end of url:` ?page=1&size=10")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Notes")})
    @GetMapping("/fromlist/{ids}")
    public Page<Note> getAllNotesFromList(@PathVariable Set<Long> ids, Pageable pageable) {
        final PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), new Sort(
                new Sort.Order(Sort.Direction.ASC, "dateTimeCreated")));
        Iterable<Note> notes = noteRepository.findByIdIn(ids, pageRequest);
        List<Note> noteList = Lists.newArrayList(notes);
        return new PageImpl<>(noteList, pageable, noteList.size());
    }
}
