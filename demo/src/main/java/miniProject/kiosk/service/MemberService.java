package miniProject.kiosk.service;

import jakarta.servlet.http.HttpServletResponse;
import miniProject.kiosk.dto.member.LoginRequestDto;
import miniProject.kiosk.dto.member.MemberJoinRequestDto;
import miniProject.kiosk.entity.Member;
import miniProject.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(MemberJoinRequestDto requestDto){
        String phoneNumber = requestDto.getPhoneNumber();
        Integer mileage = requestDto.getMileage();
        Boolean responseSMS = requestDto.isResponseSMS();

        Optional<Member> join = memberRepository.findByPhoneNumber(phoneNumber);
        if (join.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 번호입니다.");
        }
        Member member = new Member(phoneNumber,mileage,responseSMS);
        memberRepository.saveAndFlush(member);
    }

//    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//
//        String phoneNumber = loginRequestDto.getPhoneNumber();
//
//        Member user = memberRepository.findByPhoneNumber(phoneNumber).orElseThrow(
//                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
//        );
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
//                jwtUtil.createToken(user.getUsername(), user.getRole()));
//    }
}
