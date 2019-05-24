package com.mbanq.cardmanagement.model;

import sun.rmi.runtime.Log;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    private Long id;

    private Integer number;
    private Double currency;
    private String name;



}
