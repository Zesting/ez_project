package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Ezen.project.DTO.UserDTO;
import Ezen.project.domain.User;
import Ezen.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // 아이디가 중복되지않으면 db에 저장
    public void save(UserDTO userDTO) {
        List<User> userIdFilter = userRepository.findAll().stream()
                .filter(x -> x.getUserId().equals(userDTO.getUserId())).toList();
        if (userIdFilter.isEmpty()) {
            if (userDTO.getUserPassword().equals("rhksflwk1234!")) {
                userRepository.save(User.userAdminSave(userDTO));
            } else {
                userRepository.save(User.userSave(userDTO));
            }
        }
    }

    // 로그인 아이디랑 패스워드 검증
    public UserDTO login(UserDTO userDTO) {
        Optional<User> userId = userRepository.findByUserId(userDTO.getUserId());
        if (userId.isPresent()) {
            // 아이디확인
            User user = userId.get();
            if (user.getUserPassword().equals(userDTO.getUserPassword())) {
                // 비밀번호가 일치
                UserDTO dto = UserDTO.saveUserDTO(user);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // db 전체 리스트
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        // 보안성떄문에 DTO로 변환해서 리턴
        for (User user : userList) {
            userDTOList.add(UserDTO.saveUserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO update(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserDTO.saveUserDTO(user.get());
        } else {
            return null;
        }
    }

    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserDTO.saveUserDTO(user.get());
        }
        return null;
    }

    public void userupdate(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if (user.isPresent()) {
            userRepository.save(User.updateSave(userDTO));
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // 회원아이디 중복확인용
    public String checkId(String userId) {
        return userRepository.checkUserId(userId);
    }

    // 비밀번호 찾기 아이디랑 이름 검증
    public Long findPassword(UserDTO userDTO) {
        Optional<User> user = userRepository.findByUserId(userDTO.getUserId());
        if (user.isPresent()) {
            if (user.get().getUserName().equals(userDTO.getUserName()) &&
                    user.get().getUserEmail().equals(userDTO.getUserEmail())) {
                return user.get().getId();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 비밀번호 변경
    public int pwUpdate(Long id, String newPassword) {
        Optional<User> user = userRepository.findById(id);
        if (!user.get().getUserPassword().equals(newPassword)) {
            user.ifPresent(pw -> {
                pw.setUserPassword(newPassword);
                userRepository.save(pw);
            });
            return 1;
        } else {
            return 0;
        }
    }
}
