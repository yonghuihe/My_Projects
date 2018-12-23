package com.company.crm.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu {
    private Long id;

    private String text;

    private Boolean state;

    private String attributes;

    private List<Menu> children = new ArrayList<>();
    
    private Permission permission;
}