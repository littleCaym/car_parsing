package alex.avito.car_parsing.services;

import static org.junit.jupiter.api.Assertions.*;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import alex.avito.car_parsing.repositories.CarRepo;
import alex.avito.car_parsing.repositories.SessionRepo;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarServiceImplTest {

	@Autowired
	CarServiceImpl carService;

	@Autowired
	SessionRepo sessionRepo;

	@Autowired
	CarRepo carRepo;

	@Test
	void saveSession() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Session session = new Session();
		session.setTimestamp(timestamp);
		carService.saveSession(session);
		assertEquals(timestamp, sessionRepo.findFirstByTimestamp(timestamp).getTimestamp());
	}

	@Test
	void saveCar() {
		Car car = new Car();
		car.setModel("Nissan Micro");
		Session session = new Session();
		session.setTimestamp(new Timestamp(System.currentTimeMillis()));
		car.setSession(session);

		Link searchLink = carService.getAllLinksFromDb().get(2);
		car.setSearchLink(searchLink);

		carService.saveCar(car);
		assertEquals(car.getModel(), carRepo.findDistinctFirstByModel(car.getModel()).getModel());
	}

	@Test
	void getAllLinksFromDb() {
		assertFalse(carService.getAllLinksFromDb().isEmpty());
	}

	@Test
	void getAllCarsByOrderByModelAsc() {
	}

	@Test
	void getAllCarsByOrderByPriceAsc() {
	}

	@Test
	void getAllCarsByOrderByUploadDateAsc() {
	}

	@Test
	void getAllCarsByOrderByYearAsc() {
	}

	@Test
	void getCarsByModelOrderByModelAsc() {
	}

	@Test
	void getCarsByModelOrderByYearAsc() {
	}

	@Test
	void getCarsByModelOrderByPriceAsc() {
	}

	@Test
	void getCarsByModelOrderByUploadDateAsc() {
	}

	@Test
	void getMiddlePriceForAllCarsByYear() {
	}

	@Test
	void checkLastUploadDateEqualsDate() {
	}

	@Test
	void isExistCarByLink() {
	}

}