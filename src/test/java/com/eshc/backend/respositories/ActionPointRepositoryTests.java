package com.eshc.backend.respositories;

import com.eshc.backend.EshcBackendApplication;
import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.models.Member;
import com.eshc.backend.models.Task;
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


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshcBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ActionPointRepositoryTests {

    private ActionPoint actionPoint1;
    private ActionPoint actionPoint2;
    private ActionPoint actionPoint3;
    private Member member1;
    private Member member2;
    private Member member3;

    @Autowired
    private ActionPointRepository actionPointRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void setup() {
        actionPoint1 = new ActionPoint("Test ActionPoint 1");
        actionPoint2 = new ActionPoint("Test ActionPoint 2");
        actionPoint3 = new ActionPoint("Test ActionPoint 3");

        member1 = new Member("John", "Smith");
        member2 = new Member("Daniel", "McCarragher");
        member3 = new Member("Lee", "Brown");

        assertEquals(actionPointRepository.save(actionPoint1),actionPoint1);
        assertEquals(actionPointRepository.save(actionPoint2),actionPoint2);
        assertEquals(actionPointRepository.save(actionPoint3),actionPoint3);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }

    @After
    public void tearDown() {
        actionPointRepository.delete(actionPoint1);
        actionPointRepository.delete(actionPoint2);
        actionPointRepository.delete(actionPoint3);

        memberRepository.delete(member1);
        memberRepository.delete(member2);
        memberRepository.delete(member3);
    }

    @Test
    public void whenFindById_returnActionPoint() {
        ActionPoint loadedActionPoint = actionPointRepository.findById((actionPoint1.getId()))
                .orElseThrow(NoSuchElementException::new);
        assertEquals(actionPoint1.getTitle(),loadedActionPoint.getTitle());
    }

    @Test
    public void fetchAll_returnListOfActionPoints(){
        List<ActionPoint> loadedActionPoints = Lists.newArrayList(actionPointRepository.findAll());
        assertEquals(loadedActionPoints.size(),3);
    }

    @Test
    public void update_actionPointReturned() {
        String newTitle = actionPoint2.getTitle() + " (updated)";
        actionPoint2.setTitle(newTitle);
        assertEquals(actionPointRepository.save(actionPoint2).getTitle(),newTitle);
    }

    @Test
    @Transactional
    public void addTasks_returnActionPointWithNewTasks() {
        Task task1 = new Task();
        Task task2 = new Task();
        taskRepository.save(task1);
        taskRepository.save(task2);
        actionPoint1.getTasks().add(task1.getId());
        actionPoint1.getTasks().add(task2.getId());
        ActionPoint savedActionPoint = actionPointRepository.save(actionPoint1);
        final List<Task> loadedTasks = Lists.newArrayList(taskRepository.findAllById(savedActionPoint.getTasks()));
        assertEquals(loadedTasks.get(0),task1);
        assertEquals(loadedTasks.get(1),task2);
    }

    @Test
    @Transactional
    public void addContributors_returnActionPointWithContributors() {
        actionPoint1.getListOfContributors().add(member1.getId());
        actionPoint1.getListOfContributors().add(member2.getId());
        ActionPoint savedActionPoint = actionPointRepository.save(actionPoint1);
        final List<Long> loadedMemberIds = Lists.newArrayList(savedActionPoint.getListOfContributors());
        assertEquals(loadedMemberIds.get(0),member1.getId());
        assertEquals(loadedMemberIds.get(1),member2.getId());
    }

    @Test
    @Transactional
    public void addWatchers_returnActionPointWithWatchers() {
        actionPoint1.getListOfWatchers().add(member1.getId());
        actionPoint1.getListOfWatchers().add(member2.getId());
        actionPoint1.getListOfWatchers().add(member3.getId());
        ActionPoint savedActionPoint = actionPointRepository.save(actionPoint1);
        final List<Long> loadedMemberIds = Lists.newArrayList(savedActionPoint.getListOfWatchers());
        assertEquals(loadedMemberIds.get(0),member1.getId());
        assertEquals(loadedMemberIds.get(1),member2.getId());
        assertEquals(loadedMemberIds.get(2),member3.getId());
    }

}
