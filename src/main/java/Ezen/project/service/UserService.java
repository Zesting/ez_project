package Ezen.project.service;

import org.springframework.stereotype.Service;

import Ezen.project.domain.User;
import Ezen.project.DTO.UserDTO;
import Ezen.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        User user = User.userSave(userDTO);
        userRepository.save(user);

    }

}
