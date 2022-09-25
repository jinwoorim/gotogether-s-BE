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

    private long totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Reservation updateReservationStatus(Status status) {
        this.status = status;
        return this;
    }

    @Builder
    public Reservation(Product product, Member member, long totalPrice, Status status) {
        this.product = product;
        this.member = member;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
