package com.duquejo.hotels.client;

import com.duquejo.hotels.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("rooms")
public interface RoomsFeignClient {
  @RequestMapping(
      method = RequestMethod.GET,
      value = "api/v1/rooms/{id}",
      consumes = "application/json"
  )
  public List<Room> getRoomByHotelId(@PathVariable long id);
}
