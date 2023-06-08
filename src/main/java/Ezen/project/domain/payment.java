package Ezen.project.domain;

import java.time.LocalDateTime;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long payId;

    @Column(name = "userId")
    // @JoinColumn(name="user_id");
    // private User user; FK 설정d
    private String userId;

    @Column(name = "reservationId")
    private Long reservationId;

    @Column(name = "payType")
    private String payType;
    
    //시간은 @CreateDate 어노테이션이 있다. 어찌할지 체크
    @Column(name = "payTime")
    private LocalDateTime payTime;

    @Column(name = "payState")
    private String payState;
}
