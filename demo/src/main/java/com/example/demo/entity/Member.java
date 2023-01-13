package com.example.demo.entity;

import jakarta.persistence.*;
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
    private String phoneNumber;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private Boolean responseSMS;

    public Member(String phoneNumber, Integer mileage, Boolean responseSMS){
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.responseSMS = responseSMS;
    }
}
