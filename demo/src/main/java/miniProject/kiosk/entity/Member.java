package miniProject.kiosk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniProject.kiosk.dto.member.MemberJoinRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Boolean smsAgreement;

    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    public Member(MemberJoinRequestDto requestDto, MemberRoleEnum role){
        this.phoneNumber = requestDto.getPhoneNumber();
        this.point = requestDto.getPoint();
        this.smsAgreement = requestDto.getSmsAgreement();
        this.role = role;
    }

}
