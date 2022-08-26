package alex.avito.car_parsing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"Session\"")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessions_id_generator")
	@SequenceGenerator(name = "sessions_id_generator", sequenceName = "sessions_session_id_seq", allocationSize = 1)
	long session_id;
	@Column
	Timestamp timestamp;

	@JsonIgnore
	@OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
	Set<Car> carSet;

}
