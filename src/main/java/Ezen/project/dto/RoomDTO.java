package Ezen.project.DTO;

import jakarta.validation.constraints.NotNull;
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
