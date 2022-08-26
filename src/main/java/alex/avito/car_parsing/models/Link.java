package alex.avito.car_parsing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"Link\"")
public class Link {
	@Id
	@Column
	long link_id;
	@Column
	String link;
	@Column
	String description;

	@JsonIgnore
	@OneToMany(mappedBy = "searchLink", fetch = FetchType.LAZY)
	Set<Car> carSet;

}
