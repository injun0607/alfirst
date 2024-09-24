package org.alham.alhamfirst.controller.todo.api;

import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetUserById() {
        ResponseEntity<TodoListDTO> response = restTemplate.getForEntity("/api/todolist/", TodoListDTO.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);


    }

}