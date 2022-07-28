package com.example.springboot.controller;

import com.example.springboot.dto.DinosauroDTO;
import com.example.springboot.service.DinosauroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/index")
public class HelloController {

    @Autowired
    DinosauroService dinosauroService;

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/")
    public String index() {
        DinosauroDTO trex = dinosauroService.getDinosauroById(1L);
        logger.debug("Dinosauro Info: n[{}]", trex);
        return trex.getName();
    }

    @GetMapping(value="/dinosaurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DinosauroDTO>> getDinoList() {
        //return new ResponseEntity<>(dinosauroService.getDinosauri(), HttpStatus.OK);
        //return ResponseEntity.ok(dinosauroService.getDinosauri());
        return ResponseEntity.ok().body(dinosauroService.getDinosauri());
    }
}
