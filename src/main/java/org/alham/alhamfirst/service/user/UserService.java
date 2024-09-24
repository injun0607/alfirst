package org.alham.alhamfirst.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDTO getUser(long userIdx) {
        return null;
    }

    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .build();
        return userRepository.save(user);
    }


    public UserDTO getUserByIdx(long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());

        return userDTO;
    }




}
