package Ezen.project.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "payment")
public class Payment {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    @Column(name = "id")
    private Long id; // 결제 고유 번호

    @Column(name = "userId")
    // @JoinColumn(name="user_id");
    // private User user; FK 설정d
    private String userId; // 회원 고유 번호

    @Column(name = "reservationId")
    private String reservationId; // 예약 고유 번호

    @Column(name = "payType")
    private String payment_method_type; // 결제 방법

    @Column(name = "payState")
    private String payState; // 결제 상태

    // 카카오페이 에서 받아오는 값들
    @Column(name = "tid")
    private String tid; // 카카오페이 고유 번호

    @Column(name = "amount")
    private int paymentAmount; // 결제 금액

    @Column(name = "cardName")
    private String cardName; // 카드 정보

    @CreationTimestamp
    @Column(name = "approvedAt")
    private Date approved_at; // 결제 시간

    @Column(name = "roomName")
    private String roomName;

}
