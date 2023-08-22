package com.duquejo.hotels.controller;

import com.duquejo.hotels.config.ServiceConfiguration;
import com.duquejo.hotels.model.Hotel;
import com.duquejo.hotels.model.HotelRooms;
import com.duquejo.hotels.model.Properties;
import com.duquejo.hotels.service.HotelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
public class HotelController {

  private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

  @Autowired
  private HotelService service;

  @Autowired
  private ServiceConfiguration configuration;

  @GetMapping("/hotels")
  public List<Hotel> getHotels() {
    logger.info("getHotels start");
    return this.service.findAll();
  }

  @GetMapping("hotels/{hotelId}")
  @CircuitBreaker(name = "getHotelByIdSupportCB", fallbackMethod = "getHotelByIdFallback")
  public HotelRooms getHotelById(
      @PathVariable long hotelId
  ) {
    logger.info("getHotelById start");
    return this.service.findHotelById(hotelId);
  }

  @GetMapping("hotels/retry/{hotelId}")
  @Retry(name = "getHotelByIdSupportRetry", fallbackMethod = "getHotelByIdFallback")
  public HotelRooms getHotelByIdRetry(
      @PathVariable long hotelId
  ) {
    return this.service.findHotelById(hotelId);
  }

  /**
   * Circuit breaker fallback
   * @param hotelId Hotel ID
   * @param thw Thrown exception
   */
  public HotelRooms getHotelByIdFallback(
      @PathVariable long hotelId,
      Throwable thw
  ) {
    logger.error(thw.toString());
    return this.service.findHotelByIdWithoutRooms(hotelId);
  }

  @GetMapping("/hotels/read/properties")
  public String getProperties() throws JsonProcessingException {
    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties = new Properties(configuration.getMsg(), configuration.getBuildVersion(), configuration.getMailDetails());
    return objectWriter.writeValueAsString(properties);
  }
}
