package miniProject.kiosk.service;

import miniProject.kiosk.dto.member.MemberJoinRequestDto;
import miniProject.kiosk.entity.Member;
import miniProject.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(MemberJoinRequestDto requestDto){
        String phoneNumber = requestDto.getPhoneNumber();
        Integer mileage = requestDto.getMileage();
        Boolean responseSMS = requestDto.isResponseSMS();

        List<Member> join = memberRepository.findAllByPhoneNumber(phoneNumber);
        if (join != null) {
            throw new IllegalArgumentException("이미 등록된 번호입니다.");
        }
        Member member = new Member(phoneNumber,mileage,responseSMS);
        memberRepository.saveAndFlush(member);
    }
}
