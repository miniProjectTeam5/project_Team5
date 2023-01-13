package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private boolean responseSMS;

    public Member(){
        this.phoneNumber = getPhoneNumber();
        this.mileage = getMileage();
        this.responseSMS = isResponseSMS();
    }
}
