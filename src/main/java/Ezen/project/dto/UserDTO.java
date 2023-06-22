package Ezen.project.DTO;

import java.util.Date;

import Ezen.project.domain.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private Integer userAge; // 유저 주민번호
    private String userId; // 유저 아이디
    private String userPassword; // 유저 비밀번호
    private String userName; // 유저 이름
    private String userPhoneNumber; // 유저 핸드폰번호
    private String userEmail; // 유저 이메일
    private Date joinDate;
    private Integer userAuthority;

    public static UserDTO saveUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserAge(user.getUserAge());
        userDTO.setUserId(user.getUserId());
        userDTO.setUserPassword(user.getUserPassword());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setJoinDate(user.getJoinDate());
        userDTO.setUserPhoneNumber(user.getUserPhoneNumber());
        userDTO.setUserAuthority(user.getUserAuthority());
        return userDTO;
    }
}
