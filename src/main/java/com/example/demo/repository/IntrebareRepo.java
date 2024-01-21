package com.example.demo.repository;

import com.example.demo.domain.Curs;
import com.example.demo.domain.Intrebare;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntrebareRepo extends JpaRepository<Intrebare, Long> {
  Optional<List<Intrebare>> findAllByCurs(Curs curs);
}
