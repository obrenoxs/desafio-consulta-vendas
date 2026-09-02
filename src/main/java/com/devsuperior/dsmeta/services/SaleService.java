package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate maxLocalDate;

		if (maxDate.isBlank()) {
			maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxLocalDate = LocalDate.parse(maxDate);
		}

		LocalDate minLocalDate;
		if (minDate.isBlank()) {
			minLocalDate = maxLocalDate.minusYears(1L);
		} else {
			minLocalDate = LocalDate.parse(minDate);
		}

		return repository.searchSales(minLocalDate, maxLocalDate, name, pageable);
	}

	public List<SaleSummaryDTO> summary(String minDate, String maxDate) {
		LocalDate maxLocalDate;

		if (maxDate.isBlank()) {
			maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxLocalDate = LocalDate.parse(maxDate);
		}

		LocalDate minLocalDate;
		if (minDate.isBlank()) {
			minLocalDate = maxLocalDate.minusYears(1L);
		} else {
			minLocalDate = LocalDate.parse(minDate);
		}

		return repository.searchSummary(minLocalDate, maxLocalDate);
	}
}
