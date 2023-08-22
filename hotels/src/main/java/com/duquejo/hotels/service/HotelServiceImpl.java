package com.duquejo.hotels.service;

import com.duquejo.hotels.client.RoomsFeignClient;
import com.duquejo.hotels.dao.HotelDao;
import com.duquejo.hotels.model.Hotel;
import com.duquejo.hotels.model.HotelRooms;
import com.duquejo.hotels.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService{

  @Autowired
  private HotelDao hotelDao;

//  @Autowired
//  private RestTemplate restClient;

  @Autowired
  RoomsFeignClient client;

  @Override
  public List<Hotel> findAll() {
    return (List<Hotel>) hotelDao.findAll();
  }

  @Override
  public HotelRooms findHotelById(long hotelId) {
    HotelRooms response = new HotelRooms();
    Optional<Hotel> hotel = this.hotelDao.findById(hotelId);

    //    Map<String, Long> pathVariable = new HashMap<String, Long>();
    //    pathVariable.put("id", hotelId);
    //    List<Room> rooms = Arrays.asList(restClient.getForObject(
    //        "http://localhost:8081/api/v1/rooms/{id}", // URL
    //        Room[].class, // ReturnType
    //        pathVariable // Path-variable or null
    //    ));

    List<Room> rooms = client.getRoomByHotelId(hotelId);

    response.setHotelId(hotel.get().getHotelId());
    response.setHotelName(hotel.get().getHotelName());
    response.setHotelAddress(hotel.get().getHotelAddress());
    response.setRooms(rooms);
    return response;
  }

  @Override
  public HotelRooms findHotelByIdWithoutRooms(long hotelId) {
    HotelRooms response = new HotelRooms();
    Optional<Hotel> hotel = this.hotelDao.findById(hotelId);

    response.setHotelId(hotel.get().getHotelId());
    response.setHotelName(hotel.get().getHotelName());
    response.setHotelAddress(hotel.get().getHotelAddress());
    return response;
  }
}
