package by.Pavlov.HotelRooms.models;

import java.util.List;

public class Hotel {


    private int id;
    private List<Room> hotel;


    public Hotel(int id, List<Room> hotel) {
        this.id = id;
        this.hotel = hotel;
    }

    public Hotel() {
    }



    public int getId() {
        return id;
    }

    public List<Room> getHotel() {
        return hotel;
    }
}
