package miniProject.kiosk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "([0-9]+$)",message = "숫자만 입력해주세요")
    private String phoneNumber;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private Boolean responseSMS;

    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    public Member(String phoneNumber, Integer mileage, Boolean responseSMS, MemberRoleEnum role){
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.responseSMS = responseSMS;
        this.role = role;
    }

}
