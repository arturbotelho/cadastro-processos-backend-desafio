package gov.tj.pe.cadProcesso.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gov.tj.pe.cadProcesso.model.CourtProcess;
import gov.tj.pe.cadProcesso.repository.CourtProcessRepository;

@Service
public class CourtProcessService {

	private final CourtProcessRepository courtProcessRepository;

	public CourtProcessService(CourtProcessRepository courtProcessRepository) {
		this.courtProcessRepository = courtProcessRepository;
	}

	// Create or Update CourtProcess
	public CourtProcess saveCourtProcess(CourtProcess courtProcess) {
		return this.courtProcessRepository.save(courtProcess);
	}

	// Get CourtProcess by ID
	public Optional<CourtProcess> getCourtProcessById(Long id) {
		return this.courtProcessRepository.findById(id);
	}

	// Get all CourtProcess with pagination
	public Page<CourtProcess> getAllCourtProcess(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return this.courtProcessRepository.findAll(pageable);
	}

	// Delete CourtProcess by ID
	public void deleteCourtProcess(Long id) {
		this.courtProcessRepository.deleteById(id);
	}
}
