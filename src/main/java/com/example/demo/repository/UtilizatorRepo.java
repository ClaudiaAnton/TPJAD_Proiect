package com.example.demo.repository;

import com.example.demo.domain.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizatorRepo extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findUtilizatorByUtilizatorName(String utilizatorName);
}
