package com.example.demo.repository;

import com.example.demo.domain.Curs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursRepo extends JpaRepository<Curs, Long> {
}
