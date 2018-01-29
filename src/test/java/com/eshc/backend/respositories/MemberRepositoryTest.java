package com.eshc.backend.respositories;

import com.eshc.backend.models.Member;
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
        // Test Counting Members
        assertEquals(Long.valueOf(0), memberRepository.countAllMembers());
        assertEquals(0,memberRepository.getMembers().size());

        // Create Member then check that it exists
        Member member = new Member("Daniel","McCarragher");
        memberRepository.createMember(member);
        assertEquals(Long.valueOf(1), memberRepository.countAllMembers());
    }
}
