package com.edu.agh.fis.RentARoom.statistics.DTOs;

import java.time.LocalDate;
import java.util.List;

/**
 * DTOs for statistic about number of rooms added by date
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */

public class RoomAddedOverTimeStatisticDTO {
    private List<Long> allSaves;
    private List<LocalDate> allDates;

    public RoomAddedOverTimeStatisticDTO(List<Long> allSaves, List<LocalDate> allDates) {
        this.allSaves = allSaves;
        this.allDates = allDates;
    }

    public List<Long> getAllSaves() {
        return allSaves;
    }

    public void setAllSaves(List<Long> allSaves) {
        this.allSaves = allSaves;
    }

    public List<LocalDate> getAllDates() {
        return allDates;
    }

    public void setAllDates(List<LocalDate> allDates) {
        this.allDates = allDates;
    }
}
