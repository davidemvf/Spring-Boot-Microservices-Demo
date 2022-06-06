package com.example.springboot.dao;

import com.example.springboot.entity.Dinosauro;
import org.springframework.data.repository.CrudRepository;

public interface DinosauroRepository extends CrudRepository<Dinosauro, Long> {

    //User findOne(Long id); this is a default method
}
