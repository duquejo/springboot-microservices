package com.duquejo.rooms.service;

import com.duquejo.rooms.model.Room;

import java.util.List;

public interface RoomService {
  public List<Room> getRooms();
  public List<Room> getRoomByHotelId(long hotelId);
}
