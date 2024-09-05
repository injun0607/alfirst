package org.alham.alhamfirst.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDTO getUser(long userIdx) {
        return null;
    }
}
