package Ezen.project.domain;

import org.hibernate.annotations.DynamicUpdate;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter 및 setter 생성 어노케이션(그 외 toString() 등등 복수의 메서드도 자동 생성)
@AllArgsConstructor // 해당 class에 존재하는 모든 filed의 생성자 생성 어노테이션
@NoArgsConstructor // 기본 생성자 자동 생성 어노테이션
@Entity // DB Table에 존재하는 테이블과 매핑하는 어노테이션
@Table(name = "Room") // DB에 존재하는 테이블 중 하나를 지정하기 위한 어노테이션(여기서는 'room' Table 매핑)
@DynamicUpdate // 변경된 컬럼만 update를 수행하는 어노테이션
public class Room {

    @Id // primary key
    @Column(name = "roomId") // 컬럼과 필드 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성(auto_increment)
    private Long roomId; // 룸 고유 번호.(PK)

    @NotNull
    @Column(name = "roomName", length = 25, nullable = false)
    private String roomName; // 룸 이름.

    @NotNull
    @Column(name = "roomPrice", nullable = false)
    private int roomPrice; // 룸 기본 가격.

    @NotNull
    @Column(name = "roomType", length = 100, nullable = false)
    private String roomType; // 룸 타입(Mercury, Mars, Jupiter).
}
