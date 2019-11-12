package com.SimLab.model.dao;

import javax.persistence.*;

@Entity
public class Lab {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int labId;
    @Column(name = "name")
    private String labName;
    @Column(name = "description")
    private String labDesc;
}