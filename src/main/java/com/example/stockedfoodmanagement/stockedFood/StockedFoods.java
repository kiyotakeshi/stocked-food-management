package com.example.stockedfoodmanagement.stockedFood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author kiyota
 */
@Repository
public interface StockedFoods extends JpaRepository<StockedFood, UUID> {

}
