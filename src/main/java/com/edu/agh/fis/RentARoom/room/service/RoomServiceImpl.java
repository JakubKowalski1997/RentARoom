package com.edu.agh.fis.RentARoom.room.service;

import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room find(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElseGet(Room::new);
    }
}
