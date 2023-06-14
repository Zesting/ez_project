package Ezen.project.DTO;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class RoomDTO { // 룸 생성하기 위한 이미지 경로.

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
