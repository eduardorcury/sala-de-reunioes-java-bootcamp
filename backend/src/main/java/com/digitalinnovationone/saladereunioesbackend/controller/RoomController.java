package com.digitalinnovationone.saladereunioesbackend.controller;

import com.digitalinnovationone.saladereunioesbackend.exception.ResourceNotFoundException;
import com.digitalinnovationone.saladereunioesbackend.model.Room;
import com.digitalinnovationone.saladereunioesbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + id + " not found"));
        return ResponseEntity.ok(room);

    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(name = "id") Long id,
                                           @Valid @RequestBody Room newRoom) throws ResourceNotFoundException {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + id + " not found"));
        room.setName(newRoom.getName());
        room.setDate(newRoom.getDate());
        room.setStartHour(newRoom.getStartHour());
        room.setEndHour(newRoom.getEndHour());

        Room updatedRoom = roomRepository.save(room);
        return ResponseEntity.ok(updatedRoom);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + id + " not found"));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;

    }

}
