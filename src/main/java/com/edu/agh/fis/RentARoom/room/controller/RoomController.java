package com.edu.agh.fis.RentARoom.room.controller;

import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/rent-room", method = RequestMethod.POST)
    public String addRoom(@RequestBody Room room) {
//        Room room = new Room();
//        room.setAddress(roomDTO.getAddress());
//        room.setArea(Double.parseDouble(roomDTO.getArea()));
//        room.setDescription(roomDTO.getDescription());
//        room.setPrice(new BigDecimal(roomDTO.getPrice()));
        roomService.save(room);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }
}
