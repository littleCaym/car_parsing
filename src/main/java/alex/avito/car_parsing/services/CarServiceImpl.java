package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.repositories.CarRepo;
import alex.avito.car_parsing.repositories.LinkRepo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepo carRepo;

	@Autowired
	LinkRepo linkRepo;

	@Override
	public void saveCar(Car car) {
		carRepo.save(car);
	}


	@Override
	public List<Link> getAllLinksFromDb() {
		return linkRepo.findAll();
	}


	@Override
	public List<Car> getAllCarsByOrderByModelAsc() {
		return carRepo.findByOrderByModelAsc();
	}

	@Override
	public List<Car> getAllCarsByOrderByPriceAsc() {
		return carRepo.findByOrderByPriceAsc();
	}

	@Override
	public List<Car> getAllCarsByOrderByUploadDateAsc() {
		return carRepo.findByOrderByUploadDateAsc();
	}

	@Override
	public List<Car> getAllCarsByOrderByYearAsc() {
		return carRepo.findByOrderByYearAsc();
	}


	@Override
	public List<Car> getCarsByModelOrderByModelAsc(String carModel) {
		return carRepo.findByModelOrderByModelAsc(carModel);
	}

	@Override
	public List<Car> getCarsByModelOrderByYearAsc(String carModel) {
		return carRepo.findByModelOrderByYearAsc(carModel);
	}

	@Override
	public List<Car> getCarsByModelOrderByPriceAsc(String carModel) {
		return carRepo.findByModelOrderByPriceAsc(carModel);
	}

	@Override
	public List<Car> getCarsByModelOrderByUploadDateAsc(String carModel) {
		return carRepo.findByModelOrderByUploadDateAsc(carModel);
	}


	@Override
	public List<List<Object>> getMiddlePriceForAllCarsByYear() {
		List<List<Object>> data = carRepo.findMiddlePriceForEachDate();
		for (List<Object> smList : data) {
			smList.set(0,
					((LocalDate) smList.get(0)).getMonthValue() + "-" + ((LocalDate) smList.get(0)).getYear()
					);
		}
		return data;
	}

	@Override
	public boolean checkLastUploadDateEqualsDate(LocalDate date) {
		return carRepo.existsCarByUploadDate(date);
	}


}
