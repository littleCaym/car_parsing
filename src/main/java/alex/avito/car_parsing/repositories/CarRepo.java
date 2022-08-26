package alex.avito.car_parsing.repositories;

import alex.avito.car_parsing.models.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

	Car findDistinctFirstByModel(String CarModel);

	List<Car> findByOrderByModelAsc();
	List<Car> findByOrderByPriceAsc();
	List<Car> findByOrderByUploadDateAsc();
	List<Car> findByOrderByUploadDateDesc();
	List<Car> findByOrderByYearAsc();
	List<Car> findByOrderByLocationAsc();

	List<Car> findByModelOrderByModelAsc(String carModel);
	List<Car> findByModelOrderByYearAsc(String carModel);
	List<Car> findByModelOrderByPriceAsc(String carModel);
	List<Car> findByModelOrderByUploadDateAsc(String carModel);
	List<Car> findByModelOrderByLocationAsc(String carModel);

	@Query(value = "select uploadDate, avg(price) from Car group by uploadDate order by uploadDate")
	List<List<Object>> findUploadDateAndMiddlePriceForAllCarsGroupByUploadDate();
	@Query(value = "select uploadDate, avg(price) from Car where model = ?1 group by uploadDate order by uploadDate")
	List<List<Object>> findUploadDateAndMiddlePriceForCarsByModelGroupByUploadDate(String carModel);

}
