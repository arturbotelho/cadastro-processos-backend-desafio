package gov.tj.pe.cadProcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.tj.pe.cadProcesso.model.CourtProcess;

@Repository
public interface CourtProcessRepository extends JpaRepository<CourtProcess, Long> {

}
