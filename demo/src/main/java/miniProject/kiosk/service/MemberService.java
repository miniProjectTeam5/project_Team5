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

    public static void main(String[] args) {
        String regExp = "^010-?(\\d{4})-?(\\d{4})$";
        String mobNum1 = "0101234";
        System.out.println(mobNum1.matches(regExp));
    }

    @Transactional
    public void joinMember(MemberJoinRequestDto requestDto){
        String phoneNumber = requestDto.getPhoneNumber();
        Integer point = 0;
        Boolean smsAgreement = requestDto.getSmsAgreement();

        String regExp = "^010-(\\d{4})-(\\d{4})$";

        MemberRoleEnum role = MemberRoleEnum.MEMBER;

        if(!phoneNumber.matches(regExp)){
            throw new IllegalArgumentException("번호는 11자리의 숫자여야합니다.");
        }

        Optional<Member> join = memberRepository.findByPhoneNumber(phoneNumber);
        if (join.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 번호입니다.");
        }
        Member member = new Member(phoneNumber,point, smsAgreement,role);
        memberRepository.saveAndFlush(member);
    }

    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String phoneNumber = loginRequestDto.getPhoneNumber();

        Member member = memberRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new IllegalArgumentException("이 번호로 등록된 멤버는 없습니다.")
        );

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(member.getPhoneNumber(),member.getRole()));

    }

}
