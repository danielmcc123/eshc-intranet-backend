package com.eshc.backend.respositories;

import com.eshc.backend.EshcBackendApplication;
import com.eshc.backend.models.WorkingGroup;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshcBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkingGroupRepositoryTests {

    private WorkingGroup workingGroup1;
    private WorkingGroup workingGroup2;

    @Autowired
    private WorkingGroupRepository workingGroupRepository;

    @Before
    public void setup() {
        workingGroup1 = new WorkingGroup("Places",null);
        workingGroup2 = new WorkingGroup("Procedures",null);

        workingGroupRepository.save(workingGroup1);
        workingGroupRepository.save(workingGroup2);
    }

    @After
    public void tearDown() {
        workingGroupRepository.delete(workingGroup1);
        workingGroupRepository.delete(workingGroup2);
    }

    @Test
    public void findById_returnWorkingGroup() {
        WorkingGroup loadedWorkingGroup = workingGroupRepository.findById(workingGroup1.getId())
                .orElseThrow(NoSuchElementException::new);
        assertEquals(loadedWorkingGroup, workingGroup1);
    }

    @Test public void fetchAll_returnListOfWorkingGroups() {
        List<WorkingGroup> loadedWorkingGroups = Lists.newArrayList(workingGroupRepository.findAll());
        assertEquals(loadedWorkingGroups.size(), 2);
    }



}
