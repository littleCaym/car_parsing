package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import alex.avito.car_parsing.repositories.CarRepo;
import alex.avito.car_parsing.repositories.LinkRepo;
import alex.avito.car_parsing.repositories.SessionRepo;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepo carRepo;

	@Autowired
	LinkRepo linkRepo;

	@Autowired
	SessionRepo sessionRepo;

	@Override
	public void saveSession(Session session) {
		sessionRepo.save(session);
	}

	@Override
	public List<Session> getAllSessionsByOrderByTimestampAsc() {
		return null;
	}

	@Override
	public void saveCar(Car car) {
		carRepo.save(car);
	}


	@Override
	public List<Link> getAllLinksFromDb() {
		return linkRepo.findAll();
	}

	@Override
	public Link getLinkByDescription(String description) {
		return linkRepo.findDistinctFirstByDescription(description);
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
	public List<Car> getAllCarsByOrderByUploadDateDesc() {
		return carRepo.findByOrderByUploadDateDesc();
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
	public List<List<Object>> getMiddlePriceForAllCarsGroupBySession() {

		List<List<Object>> data = carRepo.findMiddlePriceForAllCarsGroupBySession();
		for (List<Object> smList : data) {
			//todo day must be distinct
			LocalDateTime localDateTime = ((Timestamp) smList.get(0)).toLocalDateTime();
			smList.set(0,
					 localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue()
					);
		}
		return data;
	}

	@Override
	public List<List<Object>> getMiddlePriceForCarsByModelGroupBySession(String carModel) {
		List<List<Object>> data = carRepo.findMiddlePriceForCarsByModelGroupBySession(carModel);
		for (List<Object> smList : data) {
			//todo day must be distinct
			LocalDateTime localDateTime = ((Timestamp) smList.get(0)).toLocalDateTime();
			smList.set(0,
					localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue()
			);
		}
		return data;
	}

	@Override
	public boolean checkLastUploadDateEqualsDate(LocalDate date) {
		return carRepo.existsCarByUploadDate(date);
	}

	@Override
	public boolean isExistCarByLink(String link) {
		return carRepo.existsCarByLink(link);
	}

}
