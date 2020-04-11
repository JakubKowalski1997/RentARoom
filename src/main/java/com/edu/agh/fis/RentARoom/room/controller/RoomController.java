package com.edu.agh.fis.RentARoom.room.controller;

import com.edu.agh.fis.RentARoom.room.DTOs.RoomDTO;
import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/rent-room", method = RequestMethod.POST)
    public void addRoom(@RequestBody Room room) {
//        Room room = new Room();
//        room.setAddress(roomDTO.getAddress());
//        room.setArea(Double.parseDouble(roomDTO.getArea()));
//        room.setDescription(roomDTO.getDescription());
//        room.setPrice(new BigDecimal(roomDTO.getPrice()));
        roomService.save(room);
    }

    @ResponseBody
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }
}
