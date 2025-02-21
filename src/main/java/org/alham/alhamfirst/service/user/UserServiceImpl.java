package org.alham.alhamfirst.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.AlhamCustomErrorLog;
import org.alham.alhamfirst.common.error.AlhamCustomException;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.mapper.UserMapper;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

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
        try{
            Optional<User> optionalUser = userRepository.findById(userId);
            return optionalUser.isPresent() ?
                    userMapper.createUserDTOFromEntity(optionalUser.get()) : UserDTO.getEmptyUser();
        } catch(AlhamCustomException e) {
            new AlhamCustomErrorLog(e);
            return UserDTO.getEmptyUser();
        }
    }

    @Override
    public UserDTO getUserByEncryptedId(String encryptedId) {
        try{

            long userId = CommonUtil.getDecryptedId(encryptedId);
            Optional<User> optionalUser = userRepository.findById(userId);

            return optionalUser.isPresent() ?
                    userMapper.createUserDTOFromEntity(optionalUser.get()) : UserDTO.getEmptyUser();

        } catch(AlhamCustomException e){
            new AlhamCustomErrorLog(e);
            return UserDTO.getEmptyUser();
        }

    }



}
