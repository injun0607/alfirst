package org.alham.alhamfirst.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

        Optional<User> optionalUser = userRepository.findById(userId);



        return null;
    }

    @Override
    public UserDTO updateUserInfo(UserDTO userDTO) {
        return null;
    }


}
