package com.duquejo.reservations.service;

import com.duquejo.reservations.dao.ReservationDao;
import com.duquejo.reservations.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

  @Autowired
  private ReservationDao reservationDao;

  @Override
  public List<Reservation> findAll() {
    return (List<Reservation>) reservationDao.findAll();
  }
}
