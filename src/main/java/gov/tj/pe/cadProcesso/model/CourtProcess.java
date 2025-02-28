package gov.tj.pe.cadProcesso.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourtProcess {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Long id;

	@Column(name = "npu", nullable = false)
	private String npu;

	@Column(nullable = false)
	private LocalDate creationDate;

	@Column(nullable = true)
	private LocalDate visualizationDate;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false, length = 2)
	private String uf;

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String pdfFileBase64;
}
