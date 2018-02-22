package com.eshc.backend.respositories;

import com.eshc.backend.models.Member;
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
public class MemberRepositoryTest {


    @Inject
    private MemberRepository memberRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MemberRepository.class)
                .addClass(Member.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Test
    public void createMember() {
        // Check that zero members exist currently
        assertEquals(Long.valueOf(0), memberRepository.countAllMembers());
        assertEquals(0,memberRepository.getMembers().size());

        // Create Member then check that it exists
        Member member = new Member("Daniel","McCarragher");
        memberRepository.createMember(member);
        assertEquals(Long.valueOf(1), memberRepository.countAllMembers());
    }

    @Test
    public void deleteMember(){
        memberRepository.deleteMember(memberRepository.getMembers().get(0).getId());
        assertEquals(Long.valueOf(0), memberRepository.countAllMembers());
    }

    @Test
    public void updateMember(){
        Member member = new Member("Daniel","McCarragher");
        memberRepository.createMember(member);
        Member loadedMember = memberRepository.getMember(member.getId());
        loadedMember.setFirstName("John");
        Member updatedMember = memberRepository.updateMember(loadedMember);
        assertEquals(updatedMember.getFirstName(), "John");
    }
}
