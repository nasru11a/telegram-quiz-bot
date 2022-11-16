package az.nasru11a.nurbot;

import az.nasru11a.nurbot.controller.UserController;
import az.nasru11a.nurbot.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class NurbotApplication {

	private final UserController controller;

	public static void main(String[] args) {
		SpringApplication.run(NurbotApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		UserDto dto = UserDto.builder()
//				.username("nasru11a")
//				.fullName("Nasrulla Ziyadov")
//				.build();
//
////		controller.register(dto);
//		System.out.printf(dto.getUsername() + " is registered");
//	}
}
