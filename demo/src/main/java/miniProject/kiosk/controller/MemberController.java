package miniProject.kiosk.controller;

import miniProject.kiosk.dto.member.MemberJoinRequestDto;
import miniProject.kiosk.dto.member.SecurityExceptionDto;
import miniProject.kiosk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/join")
    public ResponseEntity<SecurityExceptionDto> signup(@RequestBody MemberJoinRequestDto joinRequestDto) {
        memberService.joinMember(joinRequestDto);
        String msg = "멤버 가입에 성공했습니다.";
        return ResponseEntity.ok(new SecurityExceptionDto(200, msg));
    }
}