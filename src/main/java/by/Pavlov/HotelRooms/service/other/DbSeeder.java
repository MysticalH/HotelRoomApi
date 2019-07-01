package by.Pavlov.HotelRooms.service.other;

import by.Pavlov.HotelRooms.models.HotelTime;
import by.Pavlov.HotelRooms.models.ReservedRoom;
import by.Pavlov.HotelRooms.models.Room;
import by.Pavlov.HotelRooms.repositories.HotelTimeRepository;
import by.Pavlov.HotelRooms.repositories.ReserveRepository;
import by.Pavlov.HotelRooms.repositories.RoomsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private RoomsRepository roomsRepository;
    private ReserveRepository reserveRepository;
    private HotelTimeRepository hotelTimeRepository;


    public DbSeeder(RoomsRepository roomsRepository, ReserveRepository reserveRepository,HotelTimeRepository hotelTimeRepository) {
        this.roomsRepository = roomsRepository;
        this.reserveRepository = reserveRepository;
        this.hotelTimeRepository = hotelTimeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        HotelTime ht = new HotelTime("1","14:00", "12:00");

        Room room = new Room("1","101",2, Double.parseDouble("50"), ht);
        Room room2 = new Room("2","102",2, Double.parseDouble("45.5"),ht);
        Room room3 = new Room("3","103",3, Double.parseDouble("150"), ht);
        Room room4 = new Room("4","104",3, Double.parseDouble("160"), ht);
        Room room5 = new Room("5","penthouse",4, Double.parseDouble("1150"), ht);


        ReservedRoom rv1= new ReservedRoom("2","5d10e084626c500ae89b66c9", LocalDateTime.of(2019, 7, 6, 14,0), LocalDateTime.of(2019, 7, 8, 12,0));
        ReservedRoom rv2= new ReservedRoom("3", "5d10e04f626c500ae89b66c8", LocalDateTime.of(2019, 7, 7, 14,0), LocalDateTime.of(2019, 7, 12, 12,0));





        this.roomsRepository.deleteAll();
        this.reserveRepository.deleteAll();
        this.hotelTimeRepository.deleteAll();




        List<Room> rooms = Arrays.asList(room,room2,room3,room4,room5);
        List<ReservedRoom> rvl = Arrays.asList(rv1,rv2);
        this.hotelTimeRepository.save(ht);
        this.reserveRepository.saveAll(rvl);
        this.roomsRepository.saveAll(rooms);
    }


}
