package by.Pavlov.HotelRooms.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Rooms")
public class Room {

    @Id

    private String id;
    private String name;
    private Double price;

    private int beds;

    @DBRef
    private HotelTime timeOfWork;


    public Room(String id,String name, int beds, Double price, HotelTime hotelTime) {
        this.id = id;
        this.name = name;
        this.beds = beds;
        this.price = price;
        this.timeOfWork = hotelTime;
    }


    public Room(String id,String name, int beds, Double price) {
        this.id = id;
        this.name = name;
        this.beds = beds;
        this.price = price;
    }

    public Room(){}



    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {

        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }


    public void setTimeOfWork(HotelTime timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public HotelTime getTimeOfWork() {
        return timeOfWork;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBeds() {
        return beds;
    }
}
