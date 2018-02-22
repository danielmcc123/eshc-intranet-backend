package com.eshc.backend.respositories;

import com.eshc.backend.models.ActionPoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class ActionPointRepository {

    @PersistenceContext(name = "eshcPU")
    private EntityManager entityManager;

    @Transactional(REQUIRED)
    public ActionPoint createActionPoint(ActionPoint actionPoint){
        entityManager.persist(actionPoint);
        return actionPoint;
    }

    public ActionPoint getActionPoint(Long id){
        return entityManager.find(ActionPoint.class, id);
    }

    @Transactional(REQUIRED)
    public ActionPoint updateActionPoint(ActionPoint actionPoint){
        entityManager.merge(actionPoint);
        return actionPoint;
    }

    @Transactional(REQUIRED)
    public void deleteActionPoint(Long id){
        entityManager.remove(entityManager.getReference(ActionPoint.class, id));
    }

    public List<ActionPoint> getActionPoints(){
        TypedQuery<ActionPoint> query = entityManager.createQuery("SELECT a from ActionPoint a ORDER BY a.dateTime DESC", ActionPoint.class);
        return query.getResultList();
    }

    public Long countAllActionPoints(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) from ActionPoint a", Long.class);
        return query.getSingleResult();
    }
}
