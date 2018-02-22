package com.eshc.backend.respositories;

import com.eshc.backend.models.Member;
import com.eshc.backend.models.Note;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoteRepositoryTest {

    @Inject
    NoteRepository noteRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(NoteRepository.class)
                .addClass(Note.class)
                .addClass(Member.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Test
    public void createNote() {
        // Check that zero Notes exist currently
        assertEquals(Long.valueOf(0),noteRepository.countAllNotes());

        //Create task
        noteRepository.createNote(new Note());

        //Check that new one exists
        assertEquals(Long.valueOf(1),noteRepository.countAllNotes());
    }

    @Test
    public void deleteNote() {
        noteRepository.deleteNote(noteRepository.getNotes().get(0).getId());
        assertEquals(Long.valueOf(0),noteRepository.countAllNotes());
    }

    @Test
    public void updateNote(){
        String body = "Test body of text";

        Note note = new Note();
        noteRepository.createNote(note);
        Note loadedNote = noteRepository.getNote(note.getId());
        loadedNote.setNoteBody(body);
        Note updatedNote = noteRepository.updateNote(loadedNote);
        assertEquals(updatedNote.getNoteBody(), body);

    }
}
