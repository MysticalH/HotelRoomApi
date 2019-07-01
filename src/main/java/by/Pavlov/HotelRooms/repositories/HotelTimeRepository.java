package by.Pavlov.HotelRooms.repositories;

import by.Pavlov.HotelRooms.models.HotelTime;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface HotelTimeRepository extends CrudRepository<HotelTime, String> {


    Optional<HotelTime> findById (String id);


}
