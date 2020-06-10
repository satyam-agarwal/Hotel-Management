package com.mongodb.controller;

import com.mongodb.model.Hotel;
import com.mongodb.model.QHotel;
import com.mongodb.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/all")
    public List<Hotel> getALL(){
        return hotelRepository.findAll();
    }

    @PutMapping("/update")
    public  void updateHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
    }

    @PostMapping("/")
    public void addhotel(@RequestBody Hotel hotel){
        hotelRepository.insert(hotel);
    }

    @DeleteMapping("/all")
    public String deleteAll(){
        hotelRepository.deleteAll();
        return "Deleted all Hotels";
    }
    @GetMapping("/{id}")
    public Optional<Hotel> getById(@PathVariable String id){
       return hotelRepository.findById(id);
    }

    @GetMapping("/price/{price}")
    public List<Hotel> getByPriceLessThanprice(@PathVariable int price){
        return hotelRepository.findByPricePerNightLessThanEqual(price);
    }
    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable String country){
        QHotel qHotel = new QHotel("Hotel");
        BooleanExpression findbycountry = qHotel.address.country.eq(country);
        return (List<Hotel>) hotelRepository.findAll(findbycountry);
    }
}
