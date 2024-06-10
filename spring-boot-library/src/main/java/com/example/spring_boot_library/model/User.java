package com.example.spring_boot_library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERLIST")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
//    private Integer userAge;
    private LocalDate dateOfBirth;


    @Column(name = "membership_category")
    @Enumerated(EnumType.STRING)
    private MemberShipCategory memberShipCategory;



    public User() {
    }

    public User(Long userId, String userName, LocalDate dateOfBirth, MemberShipCategory memberShipCategory) {
        this.userId = userId;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.memberShipCategory = memberShipCategory;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public MemberShipCategory getMemberShip() {
        return memberShipCategory;
    }

    public void setMemberShip(MemberShipCategory memberShipCategory) {
        this.memberShipCategory = memberShipCategory;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
