package com.duquejo.rooms.controller;

import com.duquejo.rooms.config.ServiceConfiguration;
import com.duquejo.rooms.model.Properties;
import com.duquejo.rooms.model.Room;
import com.duquejo.rooms.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoomController {

  private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

  @Autowired
  private RoomService service;

  @Autowired
  private ServiceConfiguration configuration;

  @GetMapping("/rooms")
  public List<Room> getRooms() {
    logger.info("getRooms start");
    return this.service.getRooms();
  }

  @GetMapping("/rooms/read/properties")
  public String getProperties() throws JsonProcessingException {
    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties = new Properties(configuration.getMsg(), configuration.getBuildVersion(), configuration.getMailDetails());
    return objectWriter.writeValueAsString(properties);
  }

  @GetMapping("/rooms/{id}")
  public List<Room> getRoomByHotelId(
      @PathVariable long id
  ) {
    logger.info("getRoomByHotelId start");
    return this.service.getRoomByHotelId(id);
  }
}
