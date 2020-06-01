package com.edu.agh.fis.RentARoom.statistics.service;

import com.edu.agh.fis.RentARoom.security.service.SecurityServiceImpl;
import com.edu.agh.fis.RentARoom.statistics.model.Action;
import com.edu.agh.fis.RentARoom.statistics.model.History;
import com.edu.agh.fis.RentARoom.statistics.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for statistic about number of rooms added by date
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private SecurityServiceImpl securityService;

    /**
     * This method is used to save history record in db
     * @param model This is parameter for model to save
     * @param action This is Action enum parameter to save which action was used
     */
    @Override
    public void save(String model, Action action) {
        History history = new History();
        history.setModel(model);
        history.setAction(action);
        history.setUserName(securityService.findLoggedInUsername());
        historyRepository.save(history);
    }

    /**
     * This method is used to retrive all history records from db
     */
    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }
}
