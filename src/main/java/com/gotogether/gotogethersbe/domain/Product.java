package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String thumbnail;
    private String productName;
    private long amount;

    @Enumerated(EnumType.STRING)
    private Region country;

    @Enumerated(EnumType.STRING)
    private Ages ages;

    @Enumerated(EnumType.STRING)
    private GenderGroup together;

    @Enumerated(EnumType.STRING)
    private Companion companion;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    private Country nation;

    private String summary;
    private String detail;
    private String info;
    private String way;
    private String infoImage;
}
