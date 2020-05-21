package com.edu.agh.fis.RentARoom.statistics.service;


import com.edu.agh.fis.RentARoom.statistics.model.Action;
import com.edu.agh.fis.RentARoom.statistics.model.History;

import java.util.List;

public interface HistoryService {

    void save(String model, Action action);

    List<History> findAll();
}
