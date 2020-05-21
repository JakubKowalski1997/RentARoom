package com.edu.agh.fis.RentARoom.room.controller;

import com.edu.agh.fis.RentARoom.room.DTOs.AreaToPriceStatisticDTO;
import com.edu.agh.fis.RentARoom.room.DTOs.RoomWithMessageDTO;
import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.service.RoomService;
import com.edu.agh.fis.RentARoom.security.user.service.EmailService;
import com.edu.agh.fis.RentARoom.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addRoom(@RequestBody Room room) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        room.setUser(userService.findByUsername(username));
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

    @RequestMapping(value = "/send-message-to-room-owner", method = RequestMethod.POST)
    public String sendMailToRoomOwner(@RequestBody RoomWithMessageDTO roomWithMessageDTO) {
        emailService.sendMessageToRoomOwner(roomWithMessageDTO.getEmail(), roomWithMessageDTO.getMessage());
        return "redirect:/";
    }

}
