package com.digitalinnovationone.saladereunioesbackend.repository;

import com.digitalinnovationone.saladereunioesbackend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
