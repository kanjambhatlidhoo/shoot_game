package com.example.lobby.dto;

import lombok.Data;
import lombok.Value;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RoomCreationReq {
    @NotNull
    private String roomName;
    @NotNull
    private Long hostId;
}
