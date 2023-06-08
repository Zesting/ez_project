package Ezen.project.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import Ezen.project.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             //유저 고유번호
    @Column(name = "userAge")
    private Integer userAge;        //유저 주민번호
    @Column(name = "userAuthority")
    private Integer userAuthority;  //유저 권한
    @Column(name = "userId")
    private String userId;          //유저 아이디
    @Column(name = "userPassword")
    private String userPassword;    //유저 비밀번호
    @Column(name = "userName")
    private String userName;        //유저 이름
    @Column(name = "userPhoneNumber")
    private String userPhoneNumber; //유저 핸드폰번호
    @Column(name = "userEmail")
    private String userEmail;       //유저 이메일
    @CreationTimestamp
    @Column(name = "joinDate")
    private Timestamp joinDate;     //유저 가입날짜
    
    public static User userSave(UserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserName(userDTO.getUserName());
        user.setUserAge(userDTO.getUserAge());
        user.setUserPhoneNumber(userDTO.getUserPhoneNumber());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserAuthority(0);
        return user;
    }

    public static User userAdminSave(UserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserName(userDTO.getUserName());
        user.setUserAge(userDTO.getUserAge());
        user.setUserPhoneNumber(userDTO.getUserPhoneNumber());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserAuthority(1);
        return user;
    }
}
