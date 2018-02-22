package com.eshc.backend.rest;

import com.eshc.backend.models.Member;
import com.eshc.backend.respositories.MemberRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/members")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MemberEndpoint {

    @Inject
    private MemberRepository memberRepository;

    @POST
    public Member createMember(Member member) {
        return memberRepository.createMember(member);
    }

    @GET
    @Path("/{id}")
    public Member getMember(@PathParam("id") Long id) {
        return memberRepository.getMember(id);
    }

    @PUT
    @Path("/{id}")
    public Member updateMember(@PathParam("id") Long id, Member member) {
        System.out.println(id);
        return memberRepository.updateMember(member);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMember(@PathParam("id") Long id) {
        memberRepository.deleteMember(id);
    }

    @GET
    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    @GET
    @Path("/count")
    public Long countAllMembers() {
        return memberRepository.countAllMembers();
    }
}
