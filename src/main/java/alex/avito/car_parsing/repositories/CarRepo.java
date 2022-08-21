package alex.avito.car_parsing.repositories;

import alex.avito.car_parsing.models.Car;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * todo Document type CarsRepo
 */
@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

	Car findDistinctFirstByModel(String CarModel);

	List<Car> findByOrderByModelAsc();
	List<Car> findByOrderByPriceAsc();
	List<Car> findByOrderByUploadDateAsc();
	List<Car> findByOrderByYearAsc();

	List<Car> findByModelOrderByModelAsc(String carModel);
	List<Car> findByModelOrderByYearAsc(String carModel);
	List<Car> findByModelOrderByPriceAsc(String carModel);
	List<Car> findByModelOrderByUploadDateAsc(String carModel);

	@Query(value = "select s.timestamp, avg(c.price) from Car c join c.session s group by s.timestamp")
	List<List<Object>> findMiddlePriceForAllCarsGroupBySession();

	@Query(value = "select s.timestamp, avg(c.price) from Car c join c.session s where c.model = ?1 group by s.timestamp")
	List<List<Object>> findMiddlePriceForCarsByModelGroupBySession(String carModel);

	boolean existsCarByUploadDate(LocalDate date);

	boolean existsCarByLink(String link);



}
