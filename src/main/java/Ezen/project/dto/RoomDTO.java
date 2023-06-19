package Ezen.project.DTO;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class RoomDTO {

    @NotNull
    private Long roomId;

    @NotNull
    private String roomName;

    @NotNull
    private int roomPrice;

    @NotNull
    private String roomType;

    private String roomDetailInfo;

    private String roomImagePath;

}
