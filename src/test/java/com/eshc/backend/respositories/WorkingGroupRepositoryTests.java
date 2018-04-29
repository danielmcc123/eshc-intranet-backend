package com.eshc.backend.respositories;

import com.eshc.backend.EshcBackendApplication;
import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.WorkingGroup;
import com.eshc.backend.working.Status;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshcBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class WorkingGroupRepositoryTests {

    private WorkingGroup workingGroup1;
    private WorkingGroup workingGroup2;
    private WorkingGroup workingGroup3;

    private ActionPoint actionPoint1;
    private ActionPoint actionPoint2;
    private ActionPoint actionPoint3;

    @Autowired
    private WorkingGroupRepository workingGroupRepository;

    @Autowired
    private ActionPointRepository actionPointRepository;

    @Before
    public void setup() {
        workingGroup1 = new WorkingGroup("Places",null);
        workingGroup2 = new WorkingGroup("Procedures",null);
        workingGroup3 = new WorkingGroup("People",null);

        actionPoint1 = new ActionPoint("Test ActionPoint", Status.UNSTARTED);
        actionPoint2 = new ActionPoint("Test ActionPoint", Status.UNSTARTED);
        actionPoint3 = new ActionPoint("Test ActionPoint", Status.UNSTARTED);

        workingGroupRepository.save(workingGroup1);
        workingGroupRepository.save(workingGroup2);
        workingGroupRepository.save(workingGroup3);

        actionPointRepository.save(actionPoint1);
        actionPointRepository.save(actionPoint2);
        actionPointRepository.save(actionPoint3);

    }

    @After
    public void tearDown() {
        workingGroupRepository.deleteAll();
        actionPointRepository.deleteAll();
    }

    @Test
    public void findById_returnWorkingGroup() {
        WorkingGroup loadedWorkingGroup = workingGroupRepository.findById(workingGroup1.getId())
                .orElseThrow(NoSuchElementException::new);
        assertEquals(loadedWorkingGroup, workingGroup1);
    }

    @Test
    public void fetchAll_returnListOfWorkingGroups() {
        List<WorkingGroup> loadedWorkingGroups = Lists.newArrayList(workingGroupRepository.findAll());
        assertEquals(loadedWorkingGroups.size(), 2);
    }

    @Test
    public void update_returnUpdatedWorkingGroup() {
        String newName = workingGroup1.getName() + " (updated)";
        workingGroup1.setName(newName);
        assertEquals(workingGroupRepository.save(workingGroup1).getName(), newName);
    }

    @Test
    public void addActionPoints_returnWorkingGroup() {
        workingGroup1.getActionPoints().add(actionPoint1.getId());
        workingGroup1.getActionPoints().add(actionPoint2.getId());
        workingGroup1.getActionPoints().add(actionPoint3.getId());

        WorkingGroup savedWorkingGroup = workingGroupRepository.save(workingGroup1);
        List<ActionPoint> actionPointList = Lists.newArrayList(actionPointRepository
                .findAllById(savedWorkingGroup.getActionPoints()));

        assertTrue(actionPointList.contains(actionPoint1));
        assertTrue(actionPointList.contains(actionPoint2));
        assertTrue(actionPointList.contains(actionPoint3));
    }

    @Test
    public void addSubWorkingGroups_returnWorkingGroup() {
        workingGroup2.getSubWorkingGroups().add(workingGroup3);
        workingGroup1.getSubWorkingGroups().add(workingGroup2);
        WorkingGroup savedWorkingGroup = workingGroupRepository.save(workingGroup1);
        assertTrue(savedWorkingGroup.getSubWorkingGroups().contains(workingGroup2));
    }
}
