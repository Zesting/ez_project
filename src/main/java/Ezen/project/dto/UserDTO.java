package Ezen.project.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userAge; // 유저 주민번호
    private String userId; // 유저 아이디
    private String userPassword; // 유저 비밀번호
    private String userName; // 유저 이름
    private String userPhoneNumber; // 유저 핸드폰번호
    private String userEmail; // 유저 이메일
}
