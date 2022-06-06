package com.example.springboot.service;

import com.example.springboot.dao.DinosauroRepository;
import com.example.springboot.dto.DinosauroDTO;
import com.example.springboot.entity.Dinosauro;
import com.example.springboot.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosauroService {

    @Autowired
    DinosauroRepository dinosauroRepository;

    public DinosauroDTO getDinosauroById(Long id){
        return  ProjectUtils.fromEntityToDto(dinosauroRepository.findById(id).orElse(null), DinosauroDTO.class);
    }

    public List<DinosauroDTO> getDinosauri() {
        List<Dinosauro> dinosaurs = (List<Dinosauro>) dinosauroRepository.findAll();
        return dinosaurs
                .stream()
                .map(dinosaur -> ProjectUtils.fromEntityToDto(dinosaur, DinosauroDTO.class))
                .collect(Collectors.toList());
    }


}
