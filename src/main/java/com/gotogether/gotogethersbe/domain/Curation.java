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
public class Curation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURATION_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Ages ages;

    @Enumerated(EnumType.STRING)
    private Together together;

    @Enumerated(EnumType.STRING)
    private Companion companion;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
    private Theme theme;
}
