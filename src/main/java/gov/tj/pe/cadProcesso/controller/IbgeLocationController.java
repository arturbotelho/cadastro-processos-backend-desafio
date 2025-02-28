package gov.tj.pe.cadProcesso.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.tj.pe.cadProcesso.dtos.CityDTO;
import gov.tj.pe.cadProcesso.dtos.UfDTO;
import gov.tj.pe.cadProcesso.service.IbgeLocationService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/ibge")
@CrossOrigin(origins = "http://localhost:4200")
public class IbgeLocationController {
	
	private final IbgeLocationService service;
	
	public IbgeLocationController(IbgeLocationService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/cities/{uf}")
	public Mono<List<CityDTO>> getCitiesByUf(@PathVariable String uf) {
		return CityDTO.fromList(service.getCitiesByUf(uf).block());
	}
	
	@ResponseBody
	@GetMapping("/ufs")
	public Mono<List<UfDTO>> getUfs() {
		return UfDTO.fromList(service.getUfs().block());
	}
}
