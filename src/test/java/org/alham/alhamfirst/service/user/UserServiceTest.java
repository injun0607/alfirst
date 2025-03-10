package org.alham.alhamfirst.service.user;

import org.alham.alhamfirst.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void createUser() {

        // given
//        User user = new User("alham", 30);
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setName("alham");
//        userDTO.setAge(30);
//
//        // when;
//        User createdUser = userService.createUser(userDTO);
//        // then
//        assertThat(createdUser.getAge()).isEqualTo(user.getAge());
//        assertThat(createdUser.getName()).isEqualTo(user.getName());
    }

    @Test
    public void findUserById(){

        // given
//        User user = new User("alham", 30);
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//
//        //when
//        UserDTO findUser = userService.getUserByIdx(1L);
//
//        //then
//        assertThat(findUser).isNotNull();
//        assertThat(findUser.getName()).isEqualTo(user.getName());
//        assertThat(findUser.getAge()).isEqualTo(user.getAge());

    }


}