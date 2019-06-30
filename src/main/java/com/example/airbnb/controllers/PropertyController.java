package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Property;
import com.example.airbnb.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PropertyController
 */

@RestController
@RequestMapping(path="/api")
public class PropertyController {

    @Autowired
    PropertyRepository propertyRepository;

    //http://localhost:8080/api/properties
    @GetMapping(path="/properties", produces="application/json")
    public List<Property> displayProperties() {
        return propertyRepository.getAllProperties();
    }

    //search by address, numRooms, price
    //http://localhost:8080/api/properties/search?address=Beachside&numRooms=0&price=20000
    @GetMapping(path="/properties/search", produces="application/json")
    public List<Property> displayPropertiesByVariables(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price
        ){
        return propertyRepository.getPropertiesByVariables(address, numRooms, price);
    }

    //get properties that have at least 1 booking
    //http://localhost:8080/api/bookedProperties
    @GetMapping(path="/bookedProperties", produces="application/json")
    public List<Property> displayBookedProperties() {
        return propertyRepository.getBookedProperties();
    }

    //get properties that have at least 1 review
    //http://localhost:8080/api/reviewedProperties
    @GetMapping(path="/reviewedProperties", produces="application/json")
    public List<Property> displayReviewedProperties() {
        return propertyRepository.getReviewedProperties();
    }

    //order list of properties by price
    //http://localhost:8080/api/orderedProperties
    @GetMapping(path="/orderedProperties", produces="application/json")
    public List<Property> displayPropertiesOrderedByPrice() {
        return propertyRepository.getPropertiesOrderedByPrice();
    }
    
    //add property
    //http://localhost:8080/api/create_property?address=Safe%20House&numRooms=6&price=6000&allowSmoking=0&maxGuestNum=16
    @GetMapping(path="/create_property")
    public void addPropertycreateProperty(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price,
        @RequestParam("allowSmoking") boolean allowSmoking,
        @RequestParam("maxGuestNum") int maxGuestNum
    ){
        Property property = new Property();
        property.setAddress(address);
        property.setAllowSmoking(allowSmoking);
        property.setMaxGuestNum(maxGuestNum);
        property.setNumRooms(numRooms);
        property.setPrice(price);

        propertyRepository.addProperty(property);
    }

    //update property
    //http://localhost:8080/api/update_property?id=5&newPrice=16000
    @GetMapping(path="/update_property")
    public void updateProperty(@RequestParam int id, @RequestParam int newPrice) {
        propertyRepository.updateProperty(id, newPrice);
    }

    //delete property
    //http://localhost:8080/api/delete_property?id=6
    @GetMapping(path="/delete_property")
    public void deleteProperty(@RequestParam int id) {
        propertyRepository.deleteProperty(id);
    }

    //create property: address numRooms, price, allowSmoking, maxGuestNum
    //http://localhost:8080/api/properties
    @PostMapping(value="/properties", produces="application/json")
    public Property createUser(@RequestBody Property property) {
        propertyRepository.createProperty(property);
        return property;
    }
    
}