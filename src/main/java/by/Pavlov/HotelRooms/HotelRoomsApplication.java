package by.Pavlov.HotelRooms;

import by.Pavlov.HotelRooms.models.Role;
import by.Pavlov.HotelRooms.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HotelRoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelRoomsApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}
		};

	}



}
