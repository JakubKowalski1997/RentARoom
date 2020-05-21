package com.edu.agh.fis.RentARoom.statistics.controller;

import com.edu.agh.fis.RentARoom.statistics.DTOs.RoomAddedOverTimeStatisticDTO;
import com.edu.agh.fis.RentARoom.statistics.model.History;
import com.edu.agh.fis.RentARoom.statistics.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/rooms-added-by-date", method = RequestMethod.GET)
    @ResponseBody
    public RoomAddedOverTimeStatisticDTO userConfirmation(){
        List<History> allStatistics = historyService.findAll()
                .stream()
                .sorted(Comparator.comparing(History::getCreated))
                .collect(Collectors.toList());
        List<LocalDate> allDays = allStatistics.stream()
                .filter(distinctByKey(History::getCreated))
                .map(History::getCreated)
                .collect(Collectors.toList());
        List<Long> allCountsByDay = new ArrayList<>();
        for (LocalDate date : allDays) {
            allCountsByDay.add(allStatistics.stream().filter(statistic -> statistic.getCreated().equals(date)).count());
        }
        return new RoomAddedOverTimeStatisticDTO(allCountsByDay,allDays);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
