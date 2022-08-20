package alex.avito.car_parsing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
	@Id
	@Column
	long link_id;
	@Column
	String link;
	@Column
	String description;

	@JsonIgnore
	@OneToMany(mappedBy = "searchLink")
	Set<Car> carSet;

	public Link(){}

	public Link(String link, String description) {
		this.link = link;
		this.description = description;
	}

	public long getLink_id() {
		return link_id;
	}

	public void setLink_id(long id) {
		this.link_id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Car> getCarSet() {
		return carSet;
	}

	public void setCarSet(Set<Car> carSet) {
		this.carSet = carSet;
	}

}
