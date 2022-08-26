package alex.avito.car_parsing.repositories;

import alex.avito.car_parsing.models.Session;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {

	Session findFirstByTimestamp(Timestamp timestamp);
}
