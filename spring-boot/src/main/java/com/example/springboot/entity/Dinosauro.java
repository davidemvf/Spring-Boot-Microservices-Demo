package com.example.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "DINOSAUR")
public class Dinosauro {

    private Long id;
    private String name;
    private int age;
    private long weigth;
    private long heigth;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="AGE")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name="WEIGTH")
    public long getWeigth() {
        return weigth;
    }

    public void setWeigth(long weigth) {
        this.weigth = weigth;
    }

    @Column(name="heigth")
    public long getHeigth() {
        return heigth;
    }

    public void setHeigth(long heigth) {
        this.heigth = heigth;
    }
}
