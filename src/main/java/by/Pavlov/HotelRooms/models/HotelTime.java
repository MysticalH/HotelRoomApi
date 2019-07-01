package by.Pavlov.HotelRooms.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "HotelTime")
public class HotelTime {

    @Id
    private String id;

    private String arrivalTime;
    private String departureTime;


    public HotelTime( String id,String arrivalTime, String departureTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public HotelTime() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {

        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isTimeEmpty(HotelTime hotelTime){

        if (hotelTime.getArrivalTime().isEmpty() || hotelTime.getDepartureTime().isEmpty()){
            return true;
        }

        return false;
    }
}
