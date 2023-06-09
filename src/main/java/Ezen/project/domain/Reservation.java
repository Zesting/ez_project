package Ezen.project.domain;

import java.sql.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter 및 setter 생성 어노케이션(그 외 toString() 등등 복수의 메서드도 자동 생성)
@AllArgsConstructor // 해당 class에 존재하는 모든 filed의 생성자 생성 어노테이션
@NoArgsConstructor // 기본 생성자 자동 생성 어노테이션
@Entity // DB Table에 존재하는 테이블과 매핑하는 어노테이션
@Table(name = "reservation") // DB에 존재하는 테이블 중 하나를 지정하기 위한 어노테이션(여기서는 'reservation' Table 매핑)
@DynamicUpdate // 변경된 컬럼만 update를 수행하는 어노테이션
public class Reservation {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reservationId; // 예약 고유 번호(PK)

    @Column
    private Long roomId;
    // @JoinColumn(name = "room_id")
    // private Room room; // 룸 고유 번호 (FK)

    @Column
    // @JoinColumn(name = "user_id")
    private Long userId; // 회원 고유 번호.(FK)
    // 유저

    @Column
    private String reservationDate; // 예약 수행 일자.

    @Column
    private Date checkIn; // 체크 인 날짜.

    @Column
    private Date checkOut; // 체크 아웃 날짜.

    @Column
    private int finalPrice; // 최종 결제 금액.

}
