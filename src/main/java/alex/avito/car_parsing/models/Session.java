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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CollectionId;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "sessions")
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

	public long getSession_id() {
		return session_id;
	}

	public void setSession_id(long session_id) {
		this.session_id = session_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Set<Car> getCarSet() {
		return carSet;
	}

	public void setCarSet(Set<Car> carSet) {
		this.carSet = carSet;
	}

}
