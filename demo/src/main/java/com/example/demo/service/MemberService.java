package com.example.demo.service;

import com.example.demo.dto.member.MemberJoinRequestDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
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
