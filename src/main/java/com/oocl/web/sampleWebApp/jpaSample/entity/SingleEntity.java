package com.oocl.web.sampleWebApp.jpaSample.entity;

import javax.persistence.*;

@Entity
@Table
public class SingleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    public SingleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
