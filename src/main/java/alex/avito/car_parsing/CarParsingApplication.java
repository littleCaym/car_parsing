package alex.avito.car_parsing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//todo
@EnableScheduling
public class CarParsingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarParsingApplication.class, args);
	}

}
