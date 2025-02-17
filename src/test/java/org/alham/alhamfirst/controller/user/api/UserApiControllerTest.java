package org.alham.alhamfirst.controller.user.api;

import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.config.TestConfig;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.hibernate.internal.util.ExceptionHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class UserApiControllerTest {


    @Autowired
    private UserDTO userDTO;

    @Test
    public void beanTest(){

        Assertions.assertEquals(userDTO.getName(), "alham");
        Assertions.assertEquals(userDTO.getAge(), 30);
        Assertions.assertEquals(userDTO.getUserType(), UserType.ADMIN);
        Assertions.assertEquals(userDTO.getEmail(),"alham@alham.net");


        UserDTO userDTO2 = new UserDTO();
        userDTO2.setEmail("injun@0607");
        userDTO2.setAge(28);
        userDTO2.setName("injun");
        userDTO2.setUserType(UserType.BASIC);


        Assertions.assertEquals(userDTO2.getEmail(),"injun@0607");
        Assertions.assertEquals(userDTO2.getAge(),28);
        Assertions.assertEquals(userDTO2.getName(),"injun");
        Assertions.assertEquals(userDTO2.getUserType(),UserType.BASIC);


    }


    @Test
    public void exceptionTest(){

        try {

            throw new MongoTransactionException("몽고디비 오류");
        }catch (Exception e){

            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }






    }


}