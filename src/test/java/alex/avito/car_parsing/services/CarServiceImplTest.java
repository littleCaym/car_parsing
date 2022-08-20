package alex.avito.car_parsing.services;

import static org.junit.jupiter.api.Assertions.*;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.repositories.CarRepo;
import alex.avito.car_parsing.repositories.LinkRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarServiceImplTest {

	@Autowired
	CarServiceImpl carService;

	@Test
	void saveCar() {
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