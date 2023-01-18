package miniProject.kiosk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniProject.kiosk.dto.member.UpdatePointDto;

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

    public Member(String phoneNumber, Integer point, Boolean smsAgreement, MemberRoleEnum role){
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.smsAgreement = smsAgreement;
        this.role = role;
    }

    public void updatePoint(UpdatePointDto updatePointDto) {
        this.point = updatePointDto.getPoint();
    }



}
