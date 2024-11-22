package com.example.lobby.Controllers;

import com.example.lobby.Models.Room;
import com.example.lobby.Services.RoomService;
import com.example.lobby.Utils.ErrorResponse;
import com.example.lobby.dto.RoomCreationReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    private static final Logger logger = LogManager.getLogger(RoomController.class);

    // endpoint for creating a room players can join in.
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createRoom(@RequestBody RoomCreationReq roomCreationReq) {
        try {
            logger.info("Creating a room with name: {}", roomCreationReq.getRoomName());
            Room room = roomService.createRoom(roomCreationReq.getRoomName(), roomCreationReq.getHostId());
            logger.info("Room with id:{} created successfully.", room.getId());
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            getError("Error occured. Further details: " + e.getMessage());
            getError("Error cause: " + e.getCause());
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage(), e.getCause() != null ?
                    e.getCause().toString() : "No cause found.");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    private static void getError(String e) {
        logger.error(e);
    }

    // endpoint for adding players to the room.
    @PatchMapping(value = "/{roomId}/add/{playerId}")
    public ResponseEntity<Object> addPlayer(@PathVariable Long roomId, @PathVariable Long playerId) {
        try {
            logger.info("Adding player with id: {}into room with id: {}", playerId, roomId);
            Room room = roomService.addPlayer(roomId, playerId);
            logger.info("Player with id: {}into room with id: {}", playerId, roomId);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            getError("Error occured. Further details: " + e.getMessage());
            getError("Error cause: " + e.getCause());

            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage(), e.getCause() != null ?
                    e.getCause().toString() : "No cause found.");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    // endpoint for getting room details.
    @GetMapping(value = "/rooms")
    public ResponseEntity<Object> getRooms() {
        try {
            logger.info("Getting info of all rooms.");
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            getError("Error occured. Further details: " + e.getMessage());
            getError("Error cause: " + e.getCause());

            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage(), e.getCause() != null ?
                    e.getCause().toString() : "No cause found.");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    // endpoint for getting room details.
    @GetMapping(value = "/{roomId}")
    public ResponseEntity<Object> getRooms(@PathVariable Long roomId) {
        try {
            logger.info("Getting info of room with id: {}.", roomId);
            Room room = roomService.getRoom(roomId);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            getError("Error occured. Further details: " + e.getMessage());
            getError("Error cause: " + e.getCause());

            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage(), e.getCause() != null ?
                    e.getCause().toString() : "No cause found.");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
}
