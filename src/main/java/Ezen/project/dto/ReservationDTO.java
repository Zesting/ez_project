package Ezen.project.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class ReservationDTO {
    private Long reservationId; // 예약 고유 번호(PK)

    private Long roomId;

    private Long userId; // 회원 고유 번호.(FK)

    private Date reservationDate; // 예약 수행 일자.

    private Date checkIn; // 체크 인 날짜.

    private Date checkOut; // 체크 아웃 날짜.

    private int finalPrice;
}
