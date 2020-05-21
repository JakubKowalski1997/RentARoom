package com.edu.agh.fis.RentARoom.statistics.repository;

import com.edu.agh.fis.RentARoom.statistics.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
