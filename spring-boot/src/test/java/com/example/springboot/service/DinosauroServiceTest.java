package com.example.springboot.service;

import com.example.springboot.GenericTest;
import com.example.springboot.dao.DinosauroRepository;
import com.example.springboot.dto.DinosauroDTO;
import com.example.springboot.entity.Dinosauro;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class DinosauroServiceTest extends GenericTest {

    @InjectMocks
    DinosauroService dinosauroService;

    @Mock
    DinosauroRepository dinosauroRepository;

    @Test
    void getDinosauroByIdTest() {
        Dinosauro dinosauro = new Dinosauro();
        dinosauro.setId(1L);
        dinosauro.setName("Triceratops");
        dinosauro.setAge(30);
        dinosauro.setWeigth(3000);
        dinosauro.setHeigth(150);
        when(dinosauroRepository.findById(anyLong()))
                .thenReturn(Optional.of(dinosauro));
        Long id = 1L;
        DinosauroDTO result = dinosauroService.getDinosauroById(id);

        verify(dinosauroRepository).findById(anyLong());
        verifyNoMoreInteractions(dinosauroRepository);

        assertEquals("Triceratops", result.getName());
    }

    @Ignore
    @Test
    void getDinosauriTest() {
        Dinosauro dinosauro = new Dinosauro();
        dinosauro.setId(1L);
        dinosauro.setName("Triceratops");
        dinosauro.setAge(30);
        dinosauro.setWeigth(3000);
        dinosauro.setHeigth(150);
        Dinosauro dinosauro2 = new Dinosauro();
        dinosauro2.setId(2L);
        dinosauro2.setName("T-Rex");
        dinosauro2.setAge(60);
        dinosauro2.setWeigth(7000);
        dinosauro2.setHeigth(600);
        when(dinosauroRepository.findAll())
                .thenReturn(Arrays.asList(dinosauro, dinosauro2));

        //List<Dinosauro> result = dinosauroService.getDinosauri();

        verify(dinosauroRepository).findAll();
        verifyNoMoreInteractions(dinosauroRepository);

        //assertEquals("T-Rex", result.get(1).getName());
    }
}