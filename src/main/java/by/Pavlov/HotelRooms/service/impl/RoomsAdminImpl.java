package by.Pavlov.HotelRooms.service.impl;


import by.Pavlov.HotelRooms.models.HotelTime;
import by.Pavlov.HotelRooms.models.Room;
import by.Pavlov.HotelRooms.repositories.HotelTimeRepository;
import by.Pavlov.HotelRooms.repositories.RoomsAdminRepository;
import by.Pavlov.HotelRooms.service.RoomsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.xml.ws.http.HTTPException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service

public class RoomsAdminImpl implements RoomsAdminService {



    @Autowired
    RoomsAdminRepository roomsAdminRepository;

    @Autowired
    HotelTimeRepository hotelTimeRepository;


    @Override
    public Iterable<Room> showAllRooms() {
        return roomsAdminRepository.findAll();

    }

    @Override
    public Room findRoomByName(String name) {

        Room room = roomsAdminRepository.findRoomByNameEquals(name);

        return room;


    }


    @Override
    public Room createRoom(Room room) {
        room.setTimeOfWork(hotelTimeRepository.findById("1").get());
        roomsAdminRepository.save(room);
        return room;
    }

    @Override
    public Optional<Room> showRoomById(String id) {
        return roomsAdminRepository.findById(id);
    }

    @Override
    public Room update(String id, Room room) {
        Optional<Room> optRoom = roomsAdminRepository.findById(id);
        Room r = optRoom.get();
        if (r.getName() != null) {
            r.setName(room.getName());
        }
        if (r.getPrice() > 0){
            r.setPrice(room.getPrice());
        }
        if (r.getBeds() != 0) {
            r.setBeds(room.getBeds());
        }


        roomsAdminRepository.save(r);
        return r;
    }

    @Override
    public String delete(String id) {
        Optional<Room> optionalRoom = roomsAdminRepository.findById(id);
        Room room = optionalRoom.get();
        roomsAdminRepository.delete(room);

        return "";
    }


    @Override
    public HotelTime updateTimeOfWork (String arrivalS, String departureS) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        if(LocalTime.parse(arrivalS, formatter) != null && LocalTime.parse(departureS, formatter) != null) {

            HotelTime hotelTime = hotelTimeRepository.findById("1").orElseThrow(() -> new HTTPException(404));
            hotelTime.setArrivalTime(arrivalS);
            hotelTime.setDepartureTime(departureS);

            return hotelTimeRepository.save(hotelTime);
        } else{
            HotelTime hotelTime = hotelTimeRepository.findById("1").get();
            return hotelTime;
        }


    }
}
