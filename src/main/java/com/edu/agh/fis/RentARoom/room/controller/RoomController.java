package com.edu.agh.fis.RentARoom.room.controller;

import com.edu.agh.fis.RentARoom.room.DTOs.AreaToPriceStatisticDTO;
import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/rent-room", method = RequestMethod.POST)
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
    @RequestMapping(value = "/area-price-statistic", method = RequestMethod.GET)
    public AreaToPriceStatisticDTO getAreaToPriceStatistic() {
        List<Room> allRooms = roomService.findAll();
        List<BigDecimal> priceList = allRooms
                .stream()
                .map(Room::getPrice)
                .collect(Collectors.toList());
        List<Double> areaList = allRooms
                .stream()
                .map(Room::getArea)
                .collect(Collectors.toList());

        return new AreaToPriceStatisticDTO(priceList, areaList);
    }

}
