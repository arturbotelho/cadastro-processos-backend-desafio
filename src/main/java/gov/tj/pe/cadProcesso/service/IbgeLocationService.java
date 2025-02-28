package gov.tj.pe.cadProcesso.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import gov.tj.pe.cadProcesso.model.City;
import gov.tj.pe.cadProcesso.model.Uf;
import reactor.core.publisher.Mono;

@Service
public class IbgeLocationService {

	private static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v1";

	private final WebClient webClient;

	public IbgeLocationService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
	}

	public Mono<List<City>> getCitiesByUf(String uf) {
		String externalUrl = String.format("/localidades/estados/%s/municipios", uf);
		
		return this.webClient.get().uri(externalUrl).retrieve().onStatus(status -> status.is4xxClientError(), response -> {
			return Mono.error(new RuntimeException("Client error"));
		}).bodyToMono(new ParameterizedTypeReference<List<City>>() {
		});
	}

	public Mono<List<Uf>> getUfs() {
		String externalUrl = String.format("/localidades/estados");
		
		return this.webClient.get().uri(externalUrl).retrieve().onStatus(status -> status.is4xxClientError(), response -> {
			return Mono.error(new RuntimeException("Client error"));
		}).bodyToMono(new ParameterizedTypeReference<List<Uf>>() {
		});
	}
}
