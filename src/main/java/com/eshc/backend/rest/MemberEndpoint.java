package com.eshc.backend.rest;

import com.eshc.backend.models.Member;
import com.eshc.backend.respositories.MemberRepository;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/members")
@Api("Members")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MemberEndpoint {

    @Inject
    private MemberRepository memberRepository;

    @POST
    @ApiOperation(value = "Create a member", response = Member.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created a Member"),
            @ApiResponse(code = 400, message = "Bad request")})
    public Member createMember(@ApiParam(value = "Member to be created", required = true) Member member) {
        return memberRepository.createMember(member);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Return a member given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a Member"),
            @ApiResponse(code = 204, message = "Not found")})
    public Member getMember(@PathParam("id") Long id) {
        return memberRepository.getMember(id);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update a member", response = Member.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update a Member"),
            @ApiResponse(code = 204, message = "Not found")})
    public Member updateMember(@PathParam("id") Long id, @ApiParam(value = "Member to be created", required = true) Member member) {
        System.out.println(id);
        return memberRepository.updateMember(member);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete a member")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Member Deleted")
    })
    public void deleteMember(@PathParam("id") Long id) {
        memberRepository.deleteMember(id);
    }

    @GET
    @ApiOperation("Fetch all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetched Members")
    })
    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    @GET
    @Path("/count")
    @ApiOperation("Count all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation")
    })
    public Long countAllMembers() {
        return memberRepository.countAllMembers();
    }
}
