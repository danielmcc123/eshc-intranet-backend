package com.eshc.backend.respositories;

import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.Member;
import com.eshc.backend.models.Note;
import com.eshc.backend.models.Task;
import com.eshc.backend.working.Status;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ActionPointRepositoryTest {

    @Inject
    ActionPointRepository actionPointRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ActionPointRepository.class)
                .addClass(Note.class)
                .addClass(ActionPoint.class)
                .addClass(Task.class)
                .addClass(Status.class)
                .addClass(Member.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Test
    public void createActionPoint() {
        // Count all Action Points
        assertEquals(Long.valueOf(0),actionPointRepository.countAllActionPoints());
        assertEquals(0,actionPointRepository.getActionPoints().size());

        // Create an Action Point
        ActionPoint actionPoint = new ActionPoint();
        actionPoint.setTitle("Make a REST service");
        actionPointRepository.createActionPoint(actionPoint);
        assertEquals(Long.valueOf(1),actionPointRepository.countAllActionPoints());
    }

    @Test
    public void deleteActionPoint() {
    }

    @Test
    public void getActionPoints() {
        // Get the list of Action Points and assert that the first one is the one created in createActionPoint()
        ActionPoint ap = actionPointRepository.getActionPoints().get(0);
        assertEquals("Make a REST service", ap.getTitle());
    }
}
