package miniProject.kiosk.dto.member;

import lombok.Getter;

@Getter
public class MemberJoinRequestDto {
    private String phoneNumber;

    private Integer mileage;

    private Boolean smsAgreement;
}
