package com.example.cruisetrip.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Service.class, parentColumns = "id", childColumns = "service_id"),
        @ForeignKey(entity = Room.class, parentColumns = "id", childColumns = "room_id")},
        primaryKeys = {"service_id", "room_id"})
public class RoomService {
    @ColumnInfo
    private int service_id;

    @ColumnInfo
    private int room_id;

    public int getService_id() {
        return service_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
