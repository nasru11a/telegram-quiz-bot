package az.nasru11a.nurbot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class NurbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurbotApplication.class, args);
	}
}
