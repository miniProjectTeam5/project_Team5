package miniProject.kiosk.controller;

import jakarta.servlet.http.HttpServletResponse;
import miniProject.kiosk.dto.OrderRequestMsgDto;
import miniProject.kiosk.dto.member.LoginRequestDto;
import miniProject.kiosk.dto.member.MemberJoinRequestDto;
import miniProject.kiosk.dto.member.SecurityExceptionDto;
import miniProject.kiosk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/join")
    public OrderRequestMsgDto signup(@RequestBody MemberJoinRequestDto joinRequestDto, HttpServletResponse response) {
        return memberService.joinMember(joinRequestDto,response);
    }

    @PostMapping("/member/login")
    public OrderRequestMsgDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
    }
}