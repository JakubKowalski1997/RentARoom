package com.edu.agh.fis.RentARoom.statistics.service;

import com.edu.agh.fis.RentARoom.security.service.SecurityServiceImpl;
import com.edu.agh.fis.RentARoom.statistics.model.Action;
import com.edu.agh.fis.RentARoom.statistics.model.History;
import com.edu.agh.fis.RentARoom.statistics.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private SecurityServiceImpl securityService;

    @Override
    public void save(String model, Action action) {
        History history = new History();
        history.setModel(model);
        history.setAction(action);
        history.setUserName(securityService.findLoggedInUsername());
        historyRepository.save(history);
    }

    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }
}
