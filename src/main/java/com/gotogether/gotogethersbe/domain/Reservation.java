package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @CreationTimestamp
    private LocalDate reservationDate = LocalDate.now();

    private String reservationDayOfWeek = reservationDate.getDayOfWeek()
            .getDisplayName(TextStyle.SHORT, Locale.KOREAN);

    private int totalReservationPeople;
    private long totalBasicPrice;

    private String firstSelectOption;
    private int totalFirstSelectOptionCount;
    private long totalFirstSelectOptionPrice;

    private String secondSelectOption;
    private int totalSecondSelectOptionCount;
    private long totalSecondSelectOptionPrice;

    private String thirdSelectOption;
    private int totalThirdSelectOptionCount;
    private long totalThirdSelectOptionPrice;

    private long totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String duration;

    public Reservation updateReservationStatus(Status status) {
        this.status = status;
        return this;
    }

    @Builder
    public Reservation(Product product, Member member,
                       int totalReservationPeople, long totalBasicPrice,
                       String firstSelectOption, int totalFirstSelectOptionCount, long totalFirstSelectOptionPrice,
                       String secondSelectOption, int totalSecondSelectOptionCount, long totalSecondSelectOptionPrice,
                       String thirdSelectOption, int totalThirdSelectOptionCount, long totalThirdSelectOptionPrice,
                       long totalPrice, Status status, String duration) {

        this.product = product;
        this.member = member;

        this.totalReservationPeople = totalReservationPeople;
        this.totalBasicPrice = totalBasicPrice;

        this.firstSelectOption = firstSelectOption;
        this.totalFirstSelectOptionCount = totalFirstSelectOptionCount;
        this.totalFirstSelectOptionPrice = totalFirstSelectOptionPrice;

        this.secondSelectOption = secondSelectOption;
        this.totalSecondSelectOptionCount = totalSecondSelectOptionCount;
        this.totalSecondSelectOptionPrice = totalSecondSelectOptionPrice;

        this.thirdSelectOption = thirdSelectOption;
        this.totalThirdSelectOptionCount = totalThirdSelectOptionCount;
        this.totalThirdSelectOptionPrice = totalThirdSelectOptionPrice;

        this.totalPrice = totalPrice;
        this.status = status;
        this.duration = duration;
    }
}
