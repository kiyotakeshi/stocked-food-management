package com.example.stockedfoodmanagement.consumptionRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author kiyota
 */
@Repository
public interface ConsumptionRecords extends JpaRepository<ConsumptionRecord, UUID> {

}
