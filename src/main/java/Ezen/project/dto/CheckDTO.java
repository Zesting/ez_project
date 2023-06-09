package Ezen.project.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class CheckDTO {

    private Long roomId;

    private Date checkIn;

    private Date checkOut;

}
