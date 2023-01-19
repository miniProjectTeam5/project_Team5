package miniProject.kiosk.service;

import jakarta.servlet.http.HttpServletResponse;
import miniProject.kiosk.dto.member.LoginRequestDto;
import miniProject.kiosk.dto.member.MemberJoinRequestDto;
import miniProject.kiosk.entity.Member;
import miniProject.kiosk.entity.MemberRoleEnum;
import miniProject.kiosk.jwt.JwtUtil;
import miniProject.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private  final JwtUtil jwtUtil;

    @Transactional
    public void joinMember(MemberJoinRequestDto requestDto,HttpServletResponse response){
        String phoneNumber = requestDto.getPhoneNumber();

        String regExp = "^010-(\\d{4})-(\\d{4})$";

        MemberRoleEnum role = MemberRoleEnum.MEMBER;

        if(!phoneNumber.matches(regExp)){
            throw new IllegalArgumentException("번호는 11자리의 숫자여야합니다.");
        }

        Member join = memberRepository.findByPhoneNumber(phoneNumber);
        if (join != null) {
            throw new IllegalArgumentException("이미 등록된 번호입니다.");
        }
        Member member = new Member(requestDto,role);
        memberRepository.saveAndFlush(member);

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    }

        //추가로 로그인 기능이 필요하지 않을 것 같아 일단 주석처리 해 두었습니다.
//    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        String phoneNumber = loginRequestDto.getPhoneNumber();
//
//        Member member = memberRepository.findByPhoneNumber(phoneNumber);
//        if (member == null) {
//            throw new IllegalArgumentException("등록되지 않은 번호입니다.");
//        }
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
//                jwtUtil.createToken(member.getPhoneNumber(),member.getRole()));
//
//    }


}
