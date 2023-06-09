package Ezen.project.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentDTO {
  
  private Long payId;

  private String userId;

  private Long reservationId;

  private String payType;

  private LocalDateTime payTime;
  
  private String payState;

}
