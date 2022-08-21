package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

	void saveSession(Session session);

	List<Session> getAllSessionsByOrderByTimestampAsc();

	void saveCar(Car car);

	List<Car> getAllCarsByOrderByModelAsc();
	List<Car> getAllCarsByOrderByPriceAsc();
	List<Car> getAllCarsByOrderByUploadDateAsc();
	List<Car> getAllCarsByOrderByUploadDateDesc();
	List<Car> getAllCarsByOrderByYearAsc();

	List<Car> getCarsByModelOrderByModelAsc(String carModel);
	List<Car> getCarsByModelOrderByYearAsc(String carModel);
	List<Car> getCarsByModelOrderByPriceAsc(String carModel);
	List<Car> getCarsByModelOrderByUploadDateAsc(String carModel);

	List<List<Object>> getMiddlePriceForAllCarsGroupBySession();
	List<List<Object>> getMiddlePriceForCarsByModelGroupBySession(String carModel);

	List<Link> getAllLinksFromDb();
	Link getLinkByDescription(String description);
	boolean isExistCarByLink(String link);

	boolean checkLastUploadDateEqualsDate(LocalDate date);

}
