package alex.avito.car_parsing.repositories;

import alex.avito.car_parsing.models.Session;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * todo Document type SessionRepo
 */
public interface SessionRepo extends JpaRepository<Session, Long> {

	Session findFirstByTimestamp(Timestamp timestamp);
}
