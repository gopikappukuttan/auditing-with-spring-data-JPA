package com.edstem.records.auditing_with_spring_data_JPA.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private double price;
	private String createdBy;
	private LocalDate createdDate;
	private String lastModifiedBy;
	private LocalDate lastModifiedDate;
}
