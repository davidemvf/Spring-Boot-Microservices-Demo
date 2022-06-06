package com.example.springboot.controller;

import com.example.springboot.GenericTest;
import com.example.springboot.controller.HelloController;
import com.example.springboot.dto.DinosauroDTO;
import com.example.springboot.service.DinosauroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class HelloControllerTest extends GenericTest {
    @InjectMocks
    HelloController helloController;

    @Mock
    DinosauroService dinosauroServiceMocked;

    @Test
    void indexTest() {
        DinosauroDTO dinosauroDTOmocked = new DinosauroDTO();
        dinosauroDTOmocked.setName("T-Rex");
        when(dinosauroServiceMocked.getDinosauroById(anyLong()))
                .thenReturn(dinosauroDTOmocked);

        String result = helloController.index();

        verify(dinosauroServiceMocked).getDinosauroById(anyLong());
        verifyNoMoreInteractions(dinosauroServiceMocked);

        assertEquals("T-Rex", result);
    }
}