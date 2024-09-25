package org.alham.alhamfirst;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasicTest {

    @Test
    public void Test(){




        //외부 배열을 생성했을때 for문을 통해 순회할때 추가적인 객체생성을 막기위함
        List<User> users = Arrays.asList(new User(1), new User(2), new User(3), new User(4), new User(5));

        List<User> containUser = new ArrayList<>();
        int[] idxList = {1,2,3,4,5};
        for (int i = 0; i < 6; i++) {
            System.out.println(System.identityHashCode(containUser));

            containUser = users.stream().filter(user -> Arrays.stream(idxList).anyMatch(idx -> idx == user.idx)).collect(Collectors.toList());
            System.out.println(System.identityHashCode(containUser));
        }




        //given
        //when
        //then


    }

    @Test
    public void Test2(){


        //외부 배열을 생성했을때 for문을 통해 순회할때 추가적인 객체생성을 막기위함
        List<User> users = Arrays.asList(new User(1), new User(2), new User(3), new User(4), new User(5));

        int[] idxList = {1,2,3,4,5};
        for (int i = 0; i < 6; i++) {
            List<User> containUser = new ArrayList<>();
            System.out.println(System.identityHashCode(containUser));
            containUser = users.stream().filter(user -> Arrays.stream(idxList).anyMatch(idx -> idx == user.idx)).collect(Collectors.toList());
            System.out.println(System.identityHashCode(containUser));
        }

    }

    public static class User{
        private int idx;

        User(int idx){
            this.idx = idx;
        }

    }

}
