package by.Pavlov.HotelRooms.repositories;

import by.Pavlov.HotelRooms.models.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface RoomsRepository extends CrudRepository<Room, String> {





}
