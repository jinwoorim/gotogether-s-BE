package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.enums.CtgGroup;
import com.gotogether.gotogethersbe.enums.CtgNation;
import com.gotogether.gotogethersbe.enums.CtgTheme;
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
    private Long amount;

    @Enumerated(EnumType.STRING)
    private CtgNation ctgNation;

    @Enumerated(EnumType.STRING)
    private CtgGroup ctgGroup;

    @Enumerated(EnumType.STRING)
    private CtgTheme ctgTheme;

    private String summary;
    private String detail;
    private String info;
    private String way;
    private String infoImage;
}
