package com.duquejo.rooms.dao;

import com.duquejo.rooms.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomDao extends CrudRepository<Room, Long> {
  public List<Room> findByHotelId(long hotelId);
}
