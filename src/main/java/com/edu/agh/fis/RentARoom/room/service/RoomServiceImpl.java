package com.edu.agh.fis.RentARoom.room.service;

import com.edu.agh.fis.RentARoom.room.model.Room;
import com.edu.agh.fis.RentARoom.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Room Service
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * This method is used to save room
     */
    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }


    /**
     * This method is used to find all rooms
     */
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    /**
     * This method is used to find room by id
     */
    @Override
    public Room find(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElseGet(Room::new);
    }

    /**
     * This method is used to delete room by id
     */
    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
