package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

	void saveSession(Session session);

	void saveCar(Car car);

	List<Car> getAllCarsByOrderByModelAsc();
	List<Car> getAllCarsByOrderByPriceAsc();
	List<Car> getAllCarsByOrderByUploadDateAsc();
	List<Car> getAllCarsByOrderByUploadDateDesc();
	List<Car> getAllCarsByOrderByYearAsc();
	List<Car> getAllCarsOrderByLocationAscStartWithMoscow();

	List<Car> getCarsByModelOrderByModelAsc(String carModel);
	List<Car> getCarsByModelOrderByYearAsc(String carModel);
	List<Car> getCarsByModelOrderByPriceAsc(String carModel);
	List<Car> getCarsByModelOrderByUploadDateAsc(String carModel);
	List<Car> getCarsByModelOrderByLocationAscStartWithMoscow(String carModel);

	List<List<Object>> getMiddlePriceForAllCarsGroupByUploadDate();
	List<List<Object>> getMiddlePriceForCarsByModelGroupByUploadDate(String carModel);

	List<Link> getAllLinksFromDb();
	Link getLinkByDescription(String description);

}
