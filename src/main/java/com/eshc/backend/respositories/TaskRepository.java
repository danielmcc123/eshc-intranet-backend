package com.eshc.backend.respositories;

import com.eshc.backend.models.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class TaskRepository {

    @PersistenceContext(name = "eshcPU")
    private EntityManager entityManager;


    @Transactional(REQUIRED)
    public Task createTask(Task task){
        entityManager.persist(task);
        return task;
    }

    public Task getTask(Long id){
        return entityManager.find(Task.class, id);
    }

    @Transactional(REQUIRED)
    public Task updateTask(Task task){
        entityManager.persist(task);
        return task;
    }

    @Transactional(REQUIRED)
    public void deleteTask(Long id){
        entityManager.remove(entityManager.getReference(Task.class, id));
    }

    public List<Task> getTasks(){
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t ORDER BY t.Id", Task.class);
        return query.getResultList();
    }

    public Long countAllTasks(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(t) FROM Task t", Long.class);
        return query.getSingleResult();
    }
}
