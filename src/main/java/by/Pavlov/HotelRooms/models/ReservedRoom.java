package by.Pavlov.HotelRooms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;



@Document(collection = "ReservedRoom")
public class ReservedRoom {

    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotNull
    private String roomId;
    //start of booking
    @NotNull
    private LocalDateTime bookFrom;
    //end of booking
    @NotNull
    private LocalDateTime bookTo;
    private String userId;



    private int totalDays;



    public ReservedRoom(String roomId, String userId, LocalDateTime bookFrom, LocalDateTime bookTo) {
        this.roomId = roomId;
        this.bookFrom = bookFrom;
        this.bookTo = bookTo;
        this.userId = userId;

    }

    public ReservedRoom(String id) {
        this.id = id;
    }


    public ReservedRoom(){}



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    public LocalDateTime getBookTo() {
        return bookTo;
    }

    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }




}
