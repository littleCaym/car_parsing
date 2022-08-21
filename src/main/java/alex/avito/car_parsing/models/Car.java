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

@Entity
@Table(name = "cars")
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

	@ManyToOne(optional = false, cascade = CascadeType.MERGE) //todo разберись, что тут
	@JoinColumn(name = "session_id", nullable = false)
	Session session;

	@ManyToOne(optional = false, cascade= CascadeType.MERGE)
	@JoinColumn(name = "search_link_id", nullable = false)
	Link searchLink;

	public long getCar_id() {
		return car_id;
	}

	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public float getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(float engine_capacity) {
		this.engineCapacity = engine_capacity;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horse_power) {
		this.horsePower = horse_power;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmission_type) {
		this.transmissionType = transmission_type;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String body_style) {
		this.bodyStyle = body_style;
	}

	public String getWheelDriveType() {
		return wheelDriveType;
	}

	public void setWheelDriveType(String wheel_drive_type) {
		this.wheelDriveType = wheel_drive_type;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuel_type) {
		this.fuelType = fuel_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String seller_name) {
		this.sellerName = seller_name;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate upload_date) {
		this.uploadDate = upload_date;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String image_src) {
		this.imageSrc = image_src;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Link getSearchLink() {
		return searchLink;
	}

	public void setSearchLink(Link searchLink) {
		this.searchLink = searchLink;
	}

}
