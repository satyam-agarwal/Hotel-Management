package com.mongodb;

import com.mongodb.model.Address;
import com.mongodb.model.Hotel;
import com.mongodb.model.Review;
import com.mongodb.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class DbSeed implements CommandLineRunner {
    private HotelRepository hotelRepository;

    public DbSeed(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Hotel marriot = new Hotel("Marriot", 130,
                new Address("Paris","France"),
                Arrays.asList(
                        new Review("John",9),
                        new Review("Joe",10)
                )
        );
        Hotel taj = new Hotel("Taj", 200,
                new Address("Mumbai","India"),
                Arrays.asList(
                        new Review("Rajat",9),
                        new Review("Satyam",10)
                )
        );
        hotelRepository.deleteAll();
        List<Hotel> hotels =  Arrays.asList(marriot,taj);
        hotelRepository.saveAll(hotels);

    }
}
