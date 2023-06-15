package Ezen.project.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckDTO {

    @NotNull
    private Date checkIn;

    @NotNull
    private Date checkOut;

}
