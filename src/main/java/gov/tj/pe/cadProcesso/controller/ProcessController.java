package gov.tj.pe.cadProcesso.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gov.tj.pe.cadProcesso.dtos.CourtProcessDTO;
import gov.tj.pe.cadProcesso.model.CourtProcess;
import gov.tj.pe.cadProcesso.service.CourtProcessService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/process")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessController {

	private final CourtProcessService service;

	public ProcessController(CourtProcessService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/listar")
	public ResponseEntity<Page<CourtProcessDTO>> getAllCourtProcess(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<CourtProcess> courtProcessPage = service.getAllCourtProcess(page, size);
		return new ResponseEntity<>(CourtProcessDTO.fromListPaginated(courtProcessPage), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<CourtProcessDTO> getCourtProcessById(@PathVariable Long id) {
		Optional<CourtProcess> courtProcess = service.getCourtProcessById(id);

		if (courtProcess.isPresent()) {
			return ResponseEntity.ok(CourtProcessDTO.from(courtProcess.get())); // Return 200 OK with courtProcess data
		} else {
			return ResponseEntity.notFound().build(); // Return 404 if courtProcess not found
		}
	}

	@ResponseBody
	@Transactional
	@PostMapping(consumes = "multipart/form-data", path = "/")
	public ResponseEntity<CourtProcessDTO> createOrUpdateCourtProcess(@RequestParam(required = false) Long id,
			@RequestParam String npu, @RequestParam LocalDate creationDate, @RequestParam(required = false) LocalDate visualizationDate,
			@RequestParam String city, @RequestParam String uf, @RequestParam(required = false)  MultipartFile pdfFile) {

		CourtProcess savedCourtProcess = CourtProcess.builder().id(id).npu(npu).creationDate(creationDate)
				.visualizationDate(visualizationDate).city(city).uf(uf).build();

		addPdfFileAsBase64(pdfFile, savedCourtProcess, id);

		savedCourtProcess = service.saveCourtProcess(savedCourtProcess);
		return new ResponseEntity<>(CourtProcessDTO.from(savedCourtProcess), HttpStatus.CREATED);
	}

	private void addPdfFileAsBase64(MultipartFile pdfFile, CourtProcess savedCourtProcess, Long id) {
		try {
			if (pdfFile != null) {
				savedCourtProcess.setPdfFileBase64(convertToBase64(pdfFile.getBytes()));
			}else if (id != null) {
				CourtProcess courtProcess = service.getCourtProcessById(id).get();
				savedCourtProcess.setPdfFileBase64(courtProcess.getPdfFileBase64());
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private String convertToBase64(byte[] byteArray) {
		return Base64.getEncoder().encodeToString(byteArray);
	}

	@ResponseBody
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourtProcess(@PathVariable Long id) {
		service.deleteCourtProcess(id);
		return ResponseEntity.noContent().build();
	}
}
