package com.edu.agh.fis.RentARoom.room.controller;

import com.edu.agh.fis.RentARoom.room.DTOs.AreaToPriceStatisticDTO;
import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addRoom(@RequestBody Room room) {
        roomService.save(room);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable("id") Long id) {
        return roomService.find(id);
    }

    @ResponseBody
    @RequestMapping(value = "/area-price-statistic", method = RequestMethod.GET)
    public AreaToPriceStatisticDTO getAreaToPriceStatistic() {
        List<Room> allRooms = roomService.findAll();
        List<BigDecimal> priceList = allRooms
                .stream()
                .sorted(Comparator.comparing(Room::getArea))
                .map(Room::getPrice)
                .collect(Collectors.toList());
        List<Double> areaList = allRooms
                .stream()
                .sorted(Comparator.comparing(Room::getArea))
                .map(Room::getArea)
                .collect(Collectors.toList());

        return new AreaToPriceStatisticDTO(priceList, areaList);
    }

}
