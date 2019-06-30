package com.example.airbnb.repositories;

import java.util.List;

import com.example.airbnb.entities.Payment;
import com.example.airbnb.mappers.PaymentRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PaymentRepository
 */

@Transactional
@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM payment";
        RowMapper<Payment> rowMapper = new PaymentRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Payment> getPaymentsForBooking(int bookingId){
        String sql = "SELECT * FROM payment WHERE bookingId = ?";
        RowMapper<Payment> rowMapper = new PaymentRowMapper();
        return this.jdbcTemplate.query(sql, new Object[]{bookingId}, rowMapper); 
    }

}