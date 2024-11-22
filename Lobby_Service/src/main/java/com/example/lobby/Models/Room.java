package com.example.lobby.Models;

import com.example.lobby.Utils.RoomStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomName;
    private RoomStatus status;
    private final static int maxPlayers = 5;
    private List<Long> playerIds;
    private Long hostId;

    public Room(String roomName, Long hostId) {
        this.roomName = roomName;
        this.status = RoomStatus.WAITING;
        this.playerIds = new ArrayList<>();
        this.hostId = hostId;
        this.playerIds.add(hostId);
    }

    public boolean isFull() {
        return this.playerIds.size() == maxPlayers;
    }
}
