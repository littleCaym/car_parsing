package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import alex.avito.car_parsing.repositories.CarRepo;
import alex.avito.car_parsing.repositories.LinkRepo;
import alex.avito.car_parsing.repositories.SessionRepo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

	private final CarRepo carRepo;
	private final LinkRepo linkRepo;
	private final SessionRepo sessionRepo;

	@Autowired
	public CarServiceImpl(CarRepo carRepo, LinkRepo linkRepo, SessionRepo sessionRepo) {
		this.carRepo = carRepo;
		this.linkRepo = linkRepo;
		this.sessionRepo = sessionRepo;
	}

	@Override
	public void saveSession(Session session) {
		sessionRepo.save(session);
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
	public List<Car> getAllCarsOrderByLocationAscStartWithMoscow() {
		return orderCarListStartsWithMoscow(
				carRepo.findByOrderByLocationAsc()
		);
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
	public List<List<Object>> getMiddlePriceForAllCarsGroupByUploadDate() {
		return carRepo.findUploadDateAndMiddlePriceForAllCarsGroupByUploadDate();
	}

	@Override
	public List<List<Object>> getMiddlePriceForCarsByModelGroupByUploadDate(String carModel) {
		return carRepo.findUploadDateAndMiddlePriceForCarsByModelGroupByUploadDate(carModel);
	}

	@Override
	public List<Car> getCarsByModelOrderByLocationAscStartWithMoscow(String carModel) {
		return orderCarListStartsWithMoscow(
				carRepo.findByModelOrderByLocationAsc(carModel)
		);
	}

	private List<Car> orderCarListStartsWithMoscow(List<Car> initialList) {

		List<Car> toReturnList = initialList
				.stream()
				.filter(car -> car.getLocation().equals("Москва"))
				.collect(Collectors.toList());

		toReturnList.addAll(
				initialList
						.stream()
						.filter(car -> !car.getLocation().equals("Москва"))
						.collect(Collectors.toList())
		);

		return toReturnList;
	}
}
