package com.daviDev.authModule.dao;

import com.daviDev.authModule.enums.RoleEnum;
import com.daviDev.authModule.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
