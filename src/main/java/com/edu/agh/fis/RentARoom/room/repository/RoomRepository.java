package com.edu.agh.fis.RentARoom.room.repository;

import com.edu.agh.fis.RentARoom.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
