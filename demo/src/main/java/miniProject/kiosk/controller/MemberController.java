package miniProject.kiosk.controller;

import miniProject.kiosk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

//    @GetMapping("/member/join")
}
