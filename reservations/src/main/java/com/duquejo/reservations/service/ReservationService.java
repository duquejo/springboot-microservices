package com.duquejo.reservations.service;

import com.duquejo.reservations.model.Reservation;

import java.util.List;

public interface ReservationService {
  public List<Reservation> findAll();
}
