package com.duquejo.hotels.service;

import com.duquejo.hotels.model.Hotel;
import com.duquejo.hotels.model.HotelRooms;

import java.util.List;

public interface HotelService {
  public List<Hotel> findAll();
  public HotelRooms findHotelById(long hotelId);
  public HotelRooms findHotelByIdWithoutRooms(long hotelId);
}
