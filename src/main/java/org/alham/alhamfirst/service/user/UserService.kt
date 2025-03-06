package org.alham.alhamfirst.service.user;


import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;

import java.util.Optional;

public interface UserService {

    public UserDTO getUser(long userIdx);
    public User createUser(UserDTO userDTO);
    public UserDTO getUserByIdx(long userId);
    public UserDTO getUserByEncryptedId(String encryptedId);


}
