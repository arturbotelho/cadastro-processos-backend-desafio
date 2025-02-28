package gov.tj.pe.cadProcesso.dtos;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import gov.tj.pe.cadProcesso.model.CourtProcess;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourtProcessDTO {

	private Long id;
	private String npu;
	private LocalDate creationDate;
	private LocalDate visualizationDate;
	private String city;
	private String uf;
	private String pdfFileBase64;

	public static CourtProcessDTO from(CourtProcess courtProcess) {
		return CourtProcessDTO.builder().id(courtProcess.getId()).city(courtProcess.getCity())
				.creationDate(courtProcess.getCreationDate()).visualizationDate(courtProcess.getVisualizationDate())
				.npu(courtProcess.getNpu()).uf(courtProcess.getUf()).pdfFileBase64(courtProcess.getPdfFileBase64())
				.build();
	}

	public static Page<CourtProcessDTO> fromListPaginated(Page<CourtProcess> courtProcessPaginated) {
		return courtProcessPaginated.map(courtProcess -> CourtProcessDTO.from(courtProcess));
	}
}
