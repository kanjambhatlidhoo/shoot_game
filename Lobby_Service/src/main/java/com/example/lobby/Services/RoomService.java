package com.example.lobby.Services;

import com.example.lobby.Exceptions.RoomFullException;
import com.example.lobby.Models.Room;
import com.example.lobby.Repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(String roomName, Long hostId) {
        Room gameRoom = new Room(roomName,hostId);
        return roomRepository.save(gameRoom);
    }

    public Room addPlayer(Long roomId, Long playerId) {
        Room gameRoom = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found!"));
        if (!gameRoom.isFull()) {
            gameRoom.getPlayerIds().add(playerId);
        } else {
            throw new RoomFullException("Cannot add player. Room with id: " + roomId + " is already full.", new Throwable("Room reached its maximum capacity of 5 players."));
        }
        return roomRepository.save(gameRoom);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found!"));
    }
}
