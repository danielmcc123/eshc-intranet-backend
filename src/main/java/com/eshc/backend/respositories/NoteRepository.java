package com.eshc.backend.respositories;

import com.eshc.backend.models.Note;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class NoteRepository {

    @PersistenceContext(name = "eshcPU")
    private EntityManager entityManager;

    @Transactional(REQUIRED)
    public Note createNote(Note note){
        entityManager.persist(note);
        return note;
    }

    public Note getNote(Long id){
        return entityManager.find(Note.class, id);
    }

    @Transactional(REQUIRED)
    public Note updateNote(Note note){
        entityManager.merge(note);
        return note;
    }

    @Transactional(REQUIRED)
    public void deleteNote(Long id){
        entityManager.remove(entityManager.getReference(Note.class, id));
    }

    public List<Note> getNotes(){
        TypedQuery<Note> query = entityManager.createQuery("SELECT n FROM Note n ORDER BY n.dateTimeCreated DESC ", Note.class);
        return query.getResultList();
    }

    public Long countAllNotes(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(n) FROM Note n", Long.class);
        return query.getSingleResult();
    }

}
