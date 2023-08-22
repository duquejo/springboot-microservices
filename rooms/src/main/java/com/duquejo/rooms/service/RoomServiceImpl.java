package com.duquejo.rooms.service;

import com.duquejo.rooms.dao.RoomDao;
import com.duquejo.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

  @Autowired
  private RoomDao roomDao;

  @Override
  public List<Room> getRooms() {
    return (List<Room>) roomDao.findAll();
  }

  @Override
  public List<Room> getRoomByHotelId(long hotelId) {
    return roomDao.findByHotelId(hotelId);
  }
}
