package alex.avito.car_parsing.models;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"Car\"")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_id_generator")
	@SequenceGenerator(name = "cars_id_generator", sequenceName = "cars_car_id_seq", allocationSize = 1)
	long car_id;
	@Column
	String model;
	@Column
	short year;
	@Column
	long price;
	@Column
	int mileage;
	@Column(name = "engine_capacity")
	float engineCapacity;
	@Column(name = "horse_power")
	int horsePower;
	@Column(name = "transmission_type")
	String transmissionType;
	@Column(name = "body_style")
	String bodyStyle;
	@Column(name = "wheel_drive_type")
	String wheelDriveType;
	@Column(name = "fuel_type")
	String fuelType;
	@Column
	String description;
	@Column
	String location;
	@Column(name = "seller_name")
	String sellerName;
	@Column(name = "upload_date")
	LocalDate uploadDate;
	@Column(name = "image_src")
	String imageSrc;
	@Column
	String link;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "session_id", nullable = false)
	Session session;

	@ManyToOne(optional = false, cascade= CascadeType.MERGE)
	@JoinColumn(name = "search_link_id", nullable = false)
	Link searchLink;



}
