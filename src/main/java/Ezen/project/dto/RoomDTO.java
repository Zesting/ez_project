package Ezen.project.DTO;

import lombok.Data;

@Data
public class RoomDTO { // 룸 생성하기 위한 이미지 경로.

    private Long roomId;

    private String roomName;

    private int roomPrice;

    private String roomType;

    private String roomDetailInfo;

    private String roomImagePath;

}
