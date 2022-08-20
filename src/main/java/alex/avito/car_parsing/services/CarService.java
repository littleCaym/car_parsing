package alex.avito.car_parsing.services;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

	void saveCar(Car car);

	List<Link> getAllLinksFromDb();

	List<Car> getAllCarsByOrderByModelAsc();
	List<Car> getAllCarsByOrderByPriceAsc();
	List<Car> getAllCarsByOrderByUploadDateAsc();
	List<Car> getAllCarsByOrderByYearAsc();

	List<Car> getCarsByModelOrderByModelAsc(String carModel);
	List<Car> getCarsByModelOrderByYearAsc(String carModel);
	List<Car> getCarsByModelOrderByPriceAsc(String carModel);
	List<Car> getCarsByModelOrderByUploadDateAsc(String carModel);

	List<List<Object>> getMiddlePriceForAllCarsByYear();

	boolean checkLastUploadDateEqualsDate(LocalDate date);


	boolean isExistCarByLink(String link);

}
