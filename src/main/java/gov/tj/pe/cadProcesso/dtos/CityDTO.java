package gov.tj.pe.cadProcesso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import gov.tj.pe.cadProcesso.model.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
@Builder
public class CityDTO {

	private Long id;
	private String nome;

	public static CityDTO from(City city) {
		return CityDTO.builder().id(city.getId()).nome(city.getNome()).build();
	}

	public static Mono<List<CityDTO>> fromList(List<City> cityList) {
		return Mono.just(cityList.stream().map(city -> CityDTO.from(city)).collect(Collectors.toList()));
	}
}
