package com.example.airbnb.repositories;

import java.util.List;

import com.example.airbnb.entities.Property;
import com.example.airbnb.mappers.PropertyRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PropertyRepository
 */

@Transactional
@Repository
public class PropertyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropertyRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    public List<Property> getAllProperties() {
        String sql = "SELECT * FROM property";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Property> getPropertiesByVariables(String address, int numRooms, int price) {
        String sql = "SELECT * FROM property WHERE address like ? AND numRooms = ? AND price =?";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, new Object[]{"%" + address + "%", numRooms, price} ,rowMapper); 
    }

    public List<Property> getBookedProperties() {
        String sql = "SELECT distinct property.* FROM property JOIN booking ON property.id = booking.property_id";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Property> getReviewedProperties() {
        String sql = "SELECT distinct property.* FROM property JOIN review ON property.id = review.property_id";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Property> getPropertiesOrderedByPrice() {
        String sql = "SELECT * FROM property ORDER BY price";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    // public void addProperty(String address, int numRooms, int price, boolean allowSmoking, int maxGuestNum){
    //     String sql = "INSERT INTO property VALUES (?, ?, ?, ?, ?)";
    //     this.jdbcTemplate.update(sql, address, numRooms, price, allowSmoking, maxGuestNum);
    // }

    //Create record by creating new object
    public void addProperty(Property property){
        String sql = "INSERT INTO property VALUES (?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(), property.getPrice(), property.getAllowSmoking(), property.getMaxGuestNum());

        //Get the id value, which will only be auto-generated after record is created
        sql = "SELECT id FROM property WHERE address = ?";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

        property.setId(id);
    }

    public void updateProperty(int id, int newPrice){
        String sql = "UPDATE property SET price = ? WHERE id = ?";
        this.jdbcTemplate.update(sql, newPrice, id);
    }

    public void deleteProperty(int id){
        String sql = "DELETE FROM property WHERE id = ?";
        this.jdbcTemplate.update(sql, id);
    }

    public void createProperty(Property property) {
        String sql = "INSERT INTO property VALUES (?, ?, ?, ?, ?)";
        String address = property.getAddress();
        int numRooms = property.getNumRooms();
        int price = property.getPrice();
        boolean allowSmoking = property.getAllowSmoking();
        int maxGuestNum = property.getMaxGuestNum();
        this.jdbcTemplate.update(sql, address, numRooms, price, allowSmoking, maxGuestNum);

        //Variable "id" is an Interger hence the value can never be null, hence the default value is 0.
        //Get the id value from actual DB, which will only be auto-generated after record is created.
        //Then, run setId(id) method to set the variable "id" with the id value matching the DB.
        sql = "SELECT id FROM property WHERE address = ?";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

        property.setId(id);
    }
    
}