package by.Pavlov.HotelRooms.service.impl;

import by.Pavlov.HotelRooms.models.HotelTime;
import by.Pavlov.HotelRooms.models.ReservedRoom;
import by.Pavlov.HotelRooms.models.Room;
import by.Pavlov.HotelRooms.repositories.HotelTimeRepository;
import by.Pavlov.HotelRooms.repositories.ReserveRepository;
import by.Pavlov.HotelRooms.repositories.RoomsRepository;
import by.Pavlov.HotelRooms.service.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserRoomImpl implements UserRoomService {

    @Autowired
    RoomsRepository roomsRepository;
//
    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    HotelTimeRepository hotelTimeRepository;


    public UserRoomImpl() {
    }

    //search of all available rooms
    @Override
    public List<Room> findByBookFromIsBetween(String arrivalS, String departureS) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");

        LocalDateTime arrival = LocalDateTime.parse(arrivalS, formatter);
        LocalDateTime departure = LocalDateTime.parse(departureS, formatter);

        List<ReservedRoom> reservedRoomsListF = (ArrayList<ReservedRoom>) reserveRepository.findByBookFromIsBetween(arrival, departure);
        List<ReservedRoom> reservedRoomsB = (ArrayList<ReservedRoom>) reserveRepository.findByBookingBetween(arrival,departure);
        List<ReservedRoom> reservedRoomsC = (ArrayList<ReservedRoom>) reserveRepository.findByBookingAfter(arrival,departure);

        List<ReservedRoom> reservedRoomsList = new ArrayList<>();

        reservedRoomsList.addAll(reservedRoomsListF);
        reservedRoomsList.addAll(reservedRoomsB);
        reservedRoomsList.addAll(reservedRoomsC);



        if (reservedRoomsList != null && reservedRoomsList.size() > 0) {

            List<Room> freeRooms = (ArrayList<Room>) roomsRepository.findAll();

            for (ReservedRoom reservedRoom: reservedRoomsList){
                for (Room room: freeRooms){
                    if (room.getId().equals(reservedRoom.getRoomId())){
                        freeRooms.remove(room);
                        break;
                    }
                }
            }

            return freeRooms;

        }
        else{
            Iterable<Room> allRooms = roomsRepository.findAll();

            List<Room> freeRooms = new ArrayList<>();

            for (Room room : allRooms) {
                freeRooms.add(room);

            }
            return freeRooms;
        }
    }

    @Override
    public ReservedRoom reserveRoom(String roomId, String userId, String arrivalS, String departureS) {


        String arrivalF =  arrivalS + " 00:01";
        String departureF = departureS + " 00:01";

        HotelTime hotelTime = hotelTimeRepository.findById("1").get();
        if (!hotelTime.isTimeEmpty(hotelTime)){
            arrivalF = arrivalS + " " + hotelTime.getArrivalTime();
            departureF = departureS + " " + hotelTime.getDepartureTime();
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime arrival = LocalDateTime.parse(arrivalF, formatter);
        LocalDateTime departure = LocalDateTime.parse(departureF, formatter);
        ReservedRoom reservedRoom = new ReservedRoom(roomId,userId,arrival,departure);

        if (reservedRoom.getBookFrom() != null && reservedRoom.getBookTo() !=null && reservedRoom.getBookFrom().isBefore(reservedRoom.getBookTo())){
            return reserveRepository.save(reservedRoom);
        }

        reservedRoom.setId("Reservation error");
        return reservedRoom;
    }

    @Override
    public ReservedRoom save(ReservedRoom reservedRoom){


        return  reserveRepository.save(reservedRoom);
    }


}
