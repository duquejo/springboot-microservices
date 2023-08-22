package com.duquejo.reservations.dao;

import com.duquejo.reservations.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationDao extends CrudRepository<Reservation, Long> {
}
