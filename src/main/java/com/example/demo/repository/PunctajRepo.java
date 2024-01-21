package com.example.demo.repository;

import com.example.demo.domain.Curs;
import com.example.demo.domain.Intrebare;
import com.example.demo.domain.Punctaj;
import com.example.demo.domain.Utilizator;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunctajRepo extends JpaRepository<Punctaj, Long> {
  Optional<Punctaj> findFirstByUtilizatorAndCursAndIntrebare(Utilizator user, Curs curs, Intrebare intrebare);
  Optional<List<Punctaj>> findAllByUtilizator(Utilizator utilizator);
  List<Punctaj> findAllByCursIdAndUtilizatorId(Long cursId, Long utilizatorId);

  List<Punctaj> findAllByUtilizatorId(Long utilizatorId);


}
