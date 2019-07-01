package by.Pavlov.HotelRooms.controllers;

import by.Pavlov.HotelRooms.models.HotelTime;
import by.Pavlov.HotelRooms.models.Room;
import by.Pavlov.HotelRooms.service.RoomsAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Access Role ADMIN
 * require TOKEN
 */
@RestController
public class RoomAdminController {



    @Autowired
    RoomsAdminService roomsAdminService;



    /**
     * Access Role ADMIN
     * require TOKEN
     * @return all rooms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/admin/allrooms")
    @ResponseBody
    public Iterable<Room> rooms() {
        return roomsAdminService.showAllRooms();
    }





    /**
     * method: GET
     * path: /api/admin/room/name/{name}
     *
     * @return Room
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/admin/room/name/{name}")
    public Room findRoomByName(@PathVariable String name) {

        return roomsAdminService.findRoomByName(name);
    }


    /**
     *
     * method: POST
     * path: /api/admin/room/{id}
     * @param room
     * {
     *     "name": "String",
     *     "price": "Double",
     *     "beds": "int"
     * }
     * @return created room
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/admin/room/create")

    public Room save(@RequestBody Room room) {
        return roomsAdminService.createRoom(room);
    }


    /**
     * method GET
     * path: /api/admin/room/{id}
     * returns room by id
     * @param id path variable room id
     * @return room
     */
    @GetMapping( value = "/api/admin/room/{id}")
    public Optional<Room> show(@PathVariable String id) {
        return roomsAdminService.showRoomById(id);
    }




    /**
     * Update room
     * @param id of room that must be update
     * @param room new info about room
     *          {
     *      *     "name": "String",
     *      *     "price": "Double",
     *      *     "beds": "int"
     *      * }
     * @return updated room
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/api/admin/room/update/{id}")
    public Room update(@PathVariable String id, @RequestBody Room room) {

        return roomsAdminService.update(id, room);

    }


    /**
     *
     * @param id String roomId
     * @return message
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/admin/room/{id}")
    public String delete(@PathVariable String id) {
        roomsAdminService.delete(id);
        return "deleted";

    }


    /**
     * Set new time for all rooms
     * @param arrival String format HH:mm
     * @param departure String format HH:mm
     * @return Hotel time
     */
    @PutMapping(value = "/api/admin/change/time/{arrival}/{departure}")
    public HotelTime updateTimeOfWork(@PathVariable String arrival, @PathVariable String departure){

        return roomsAdminService.updateTimeOfWork(arrival,departure);

    }


}
