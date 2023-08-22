package com.duquejo.hotels.dao;

import com.duquejo.hotels.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelDao extends CrudRepository<Hotel, Long> {
}
