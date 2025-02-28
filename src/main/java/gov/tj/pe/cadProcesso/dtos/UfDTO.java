package gov.tj.pe.cadProcesso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import gov.tj.pe.cadProcesso.model.Uf;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
@Builder
public class UfDTO {

	private Long id;
	private String sigla;
	private String nome;

	public static UfDTO from(Uf uf) {
		return UfDTO.builder().id(uf.getId()).nome(uf.getNome()).sigla(uf.getSigla()).build();
	}

	public static Mono<List<UfDTO>> fromList(List<Uf> ufsList) {
		return Mono.just(ufsList.stream().map(uf -> UfDTO.from(uf)).collect(Collectors.toList()));
	}
}
