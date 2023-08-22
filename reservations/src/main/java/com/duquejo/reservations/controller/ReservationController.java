package com.duquejo.reservations.controller;

import com.duquejo.reservations.config.ServiceConfiguration;
import com.duquejo.reservations.model.Properties;
import com.duquejo.reservations.model.Reservation;
import com.duquejo.reservations.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

  private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

  @Autowired
  private ReservationService service;

  @Autowired
  private ServiceConfiguration configuration;

  @GetMapping("/reservations")
  public List<Reservation> findAll() {
    logger.info("findAll start");
    return service.findAll();
  }

  @GetMapping("/reservations/read/properties")
  public String getProperties() throws JsonProcessingException {
    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties = new Properties(configuration.getMsg(), configuration.getBuildVersion(), configuration.getMailDetails());
    return objectWriter.writeValueAsString(properties);
  }
}
