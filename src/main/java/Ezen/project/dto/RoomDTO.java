package Ezen.project.DTO;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RoomDTO {

    @NotNull
    @NotEmpty
    private Long roomId;

    @NotNull
    @NotEmpty
    private String roomName;

    @NotNull
    @NotEmpty
    private int roomPrice;

    @NotNull
    @NotEmpty
    private String roomType;

    private String roomDetailInfo;

    private String roomImagePath;

}
