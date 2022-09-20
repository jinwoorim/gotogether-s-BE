package com.gotogether.gotogethersbe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKER_ID")
    private Long id;

    private String name;
    private int age;
    private String phoneNumber;
    private Boolean role;
}
