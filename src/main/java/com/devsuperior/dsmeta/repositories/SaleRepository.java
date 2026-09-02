package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(
            "SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id, s.amount, s.date, seller.name) "
            + "FROM Sale s JOIN s.seller seller "
            + "WHERE s.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER(seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
    )
    Page<SaleMinDTO> searchSales(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name, Pageable pageable);

    @Query(
            "SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(seller.name, SUM(s.amount)) "
            + "FROM Sale s JOIN s.seller seller "
            + "WHERE s.date BETWEEN :minDate AND :maxDate "
            + "GROUP BY seller.name"
    )
    List<SaleSummaryDTO> searchSummary(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);
}
