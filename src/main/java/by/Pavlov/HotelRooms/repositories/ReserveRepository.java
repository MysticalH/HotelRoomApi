package by.Pavlov.HotelRooms.repositories;

import by.Pavlov.HotelRooms.models.ReservedRoom;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReserveRepository extends CrudRepository<ReservedRoom, String> {



    @Query("{ 'bookFrom' : { $lt : ?0}, 'bookTo' : { $gt : ?0}}}" )
    Iterable<ReservedRoom> findByBookFromIsBetween(LocalDateTime arrival, LocalDateTime departure);

    @Query(" {'bookFrom' : { $gte : ?0}, 'bookTo' : { $lte : ?1}}")
    Iterable<ReservedRoom> findByBookingBetween(LocalDateTime arrival, LocalDateTime departure);

    @Query(" {'bookFrom' : { $lt : ?1}, 'bookTo' : { $gt : ?1}}")
    Iterable<ReservedRoom> findByBookingAfter(LocalDateTime arrival, LocalDateTime departure);





    @Query("{ 'bookTo' : {  $lt: ?0 } }")
    List<ReservedRoom> findByBookToIsLessThanEqual(LocalDate arrival);

}
