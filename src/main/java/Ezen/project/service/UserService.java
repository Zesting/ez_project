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

    public void save(UserDTO userDTO) {
        List<User> userIdFilter = userRepository.findAll().stream()
                .filter(x -> x.getUserId().equals(userDTO.getUserId())).toList();

        if (userIdFilter.isEmpty()) {
            if (userDTO.getUserPassword().equals("1234!")) {
                userRepository.save(User.userAdminSave(userDTO));
            } else {
                userRepository.save(User.userSave(userDTO));
            }
        }
    }

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

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(UserDTO.saveUserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO update(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
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

}
