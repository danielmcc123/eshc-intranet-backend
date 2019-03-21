package com.eshc.backend.rest;


import com.eshc.backend.models.Member;
import com.eshc.backend.respositories.MemberRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.NoSuchElementException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@CrossOrigin
@RestController
@RequestMapping("/api/members")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MemberEndpoint {

    @Autowired
    private MemberRepository memberRepository;

    @ApiOperation(value = "Create a Member", response = Member.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully created a Member"),
            @ApiResponse(code = 400, message = "Bad request")})
    @PostMapping
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @ApiOperation("Return a Member given an Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a Member"),
            @ApiResponse(code = 204, message = "Not found")})
    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @ApiOperation(value = "Update a Member", response = Member.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update a Member"),
            @ApiResponse(code = 204, message = "Not found")})
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @ApiOperation("Delete a member")
    @ApiResponses({@ApiResponse(code = 204, message = "Member Deleted")})
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }

    @ApiOperation("Fetch all members")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched Members")})
    @GetMapping
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @ApiOperation("Count all Members")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/count")
    public Long countAllMembers() {
        return memberRepository.count();
    }
}
