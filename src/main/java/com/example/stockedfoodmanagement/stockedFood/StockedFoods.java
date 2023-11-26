package com.example.stockedfoodmanagement.stockedFood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author kiyota
 */
@Repository
public interface StockedFoods extends JpaRepository<StockedFood, UUID> {

	List<StockedFood> findAllByBestBeforeBetween(LocalDate today, LocalDate expirationDate);

}
