package alex.avito.car_parsing.repositories;

import alex.avito.car_parsing.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepo extends JpaRepository<Link, Long> {

	Link findDistinctFirstByDescription(String description);
}
