package com.edu.agh.fis.RentARoom.room.service;

import com.edu.agh.fis.RentARoom.room.model.Room;

import java.util.List;

public interface RoomService {
    void save(Room room);

    List<Room> findAll();

    Room find(Long id);

    void delete(Long id);
}
