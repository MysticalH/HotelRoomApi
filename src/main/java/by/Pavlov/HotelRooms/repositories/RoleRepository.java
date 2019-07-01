package by.Pavlov.HotelRooms.repositories;

import by.Pavlov.HotelRooms.models.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {

    Role findByRole(String role);


}
