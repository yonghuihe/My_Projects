package com.company.crm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputtime;

    private Boolean state;

    private Boolean admin;
    
    private Department dept; 
    
    private List<Role> roles = new ArrayList<>();
    //roles[0].id = 1
}