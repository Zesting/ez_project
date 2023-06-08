package Ezen.project.DTO;

import lombok.Data;

@Data
public class RoomDTO { // 룸 생성하기 위한 이미지 경로.

    // 해당 DTO에서는 roomId가 필요 없음.
    private String roomName;

    private int roomPrice;

    private String roomType;

    private String roomDetailInfo;

    private String roomImagePath;

}
