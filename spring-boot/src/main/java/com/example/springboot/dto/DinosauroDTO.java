package com.example.springboot.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DinosauroDTO {

    private Long id;
    private String name;
    private int age;
    private long weigth;
    private long heigth;
}
