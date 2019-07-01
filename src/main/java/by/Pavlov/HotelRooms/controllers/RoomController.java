package by.Pavlov.HotelRooms.controllers;


import by.Pavlov.HotelRooms.models.ReservedRoom;
import by.Pavlov.HotelRooms.models.Room;

import by.Pavlov.HotelRooms.service.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@RestController
public class RoomController {



    @Autowired
    UserRoomService userRoom;




    /**
     * method to find all free rooms between
     * two dates
     * @param arrivalS String format YYYY-MM-dd
     * @param departureS
     * @return
     */
    @RequestMapping(value = "/rooms/free/between/", method = RequestMethod.GET)
    @ResponseBody
    public List<Room> getAvailableRoomsBetweenDates(@RequestParam("from") String arrivalS, @RequestParam("to") String departureS) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");

        LocalDateTime arrival = LocalDateTime.parse(arrivalS, formatter);
        LocalDateTime departure = LocalDateTime.parse(departureS, formatter);
        List<Room> retList = userRoom.findByBookFromIsBetween(arrivalS, departureS);

        return retList;
    }


    /**
     * Reserve room by user
     * @param room String room id
     * @param user String user id
     * @param arrivalS String date of arrival YYYY-MM-DD
     * @param departureS
     */
    @RequestMapping(value = "/rooms/reservation/{room}/{user}/{from}/{to}", method = RequestMethod.PUT)
    public ReservedRoom reserveRoom(@PathVariable("room") String room,@PathVariable("user") String user, @PathVariable("from") String arrivalS, @PathVariable("to") String departureS){


        return userRoom.reserveRoom(room,user,arrivalS,departureS);

    }







}
