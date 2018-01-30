package com.eshc.backend.respositories;

import com.eshc.backend.models.Member;
import com.eshc.backend.models.Note;
import com.eshc.backend.models.Task;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskRepositoryTest {

    @Inject
    private TaskRepository taskRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TaskRepository.class)
                .addClass(Note.class)
                .addClass(Member.class)
                .addClass(Task.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Test
    public void createTask() {
        // Check that zero tasks exist currently
        assertEquals(Long.valueOf(0),taskRepository.countAllTasks());

        //Create task
        taskRepository.createTask(new Task());

        //Check that new one exists
        assertEquals(Long.valueOf(1),taskRepository.countAllTasks());
    }

    @Test
    public void deleteTask() {
        taskRepository.deleteTask(taskRepository.getTasks().get(0).getId());
        assertEquals(Long.valueOf(0),taskRepository.countAllTasks());
    }
}
