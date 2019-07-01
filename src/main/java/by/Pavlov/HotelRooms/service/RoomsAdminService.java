package by.Pavlov.HotelRooms.service;

import by.Pavlov.HotelRooms.models.HotelTime;
import by.Pavlov.HotelRooms.models.Room;

import java.util.Optional;

public interface RoomsAdminService {

    Iterable<Room> showAllRooms();

    Room findRoomByName(String name);

    Room createRoom(Room room);

    Optional<Room> showRoomById(String id);

    Room update(String id, Room room);

    String delete(String id);

    HotelTime updateTimeOfWork(String arrival, String departure);

}
