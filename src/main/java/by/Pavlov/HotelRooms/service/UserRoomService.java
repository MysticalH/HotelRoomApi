package by.Pavlov.HotelRooms.service;


import by.Pavlov.HotelRooms.models.ReservedRoom;
import by.Pavlov.HotelRooms.models.Room;


import java.util.List;

public interface UserRoomService {


    List<Room> findByBookFromIsBetween(String arrivalS, String departureS);

    ReservedRoom reserveRoom(String userId, String roomId, String arrival, String departure);
    ReservedRoom save(ReservedRoom reservedRoom);


}
