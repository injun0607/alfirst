package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.util.AESUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserMapper.class)
class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void transferTest() throws Exception {
        User user = User.builder()
                .id(1L)
                .name("alham")
                .age(30)
                .email("alham@naver.com")
                .userType(UserType.BASIC)
                .build();

        String encrypted = AESUtil.encrypt(user.getId().toString());
        UserDTO userDTO = userMapper.createUserDTOFromEntity(user);
        assertEquals(encrypted, userDTO.getId());



    }

}