package Ezen.project.DTO;

import java.sql.Date;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class CheckDTO {

    @NotNull
    private Date checkIn;

    @NotNull
    private Date checkOut;

}
