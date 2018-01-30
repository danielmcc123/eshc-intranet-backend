package com.eshc.backend.rest;

import com.eshc.backend.models.Member;
import com.eshc.backend.respositories.MemberRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/members")
public class MemberEndpoint {

    @Inject
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.createMember(member);
    }

    public Member getMember(Long id) {
        return memberRepository.getMember(id);
    }

    public Member updateMember(Member member) {
        return memberRepository.updateMember(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteMember(id);
    }

    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    @Path("/count")
    @Produces(APPLICATION_JSON)
    @GET
    public Long countAllMembers() {
        return memberRepository.countAllMembers();
    }


}
