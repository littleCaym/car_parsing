package alex.avito.car_parsing.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_id_generator")
	@SequenceGenerator(name = "cars_id_generator", sequenceName = "cars_id_seq", allocationSize = 1)
	long id;
	@Column
	String model;
	@Column
	short year;
	@Column
	long price;
	@Column(name = "image_src")
	String imageSrc;
	@Column(name = "specific_params")
	String specificParams;
	@Column(name = "upload_date")
	LocalDate uploadDate;
	@Column
	String link;
	@Column(name = "seller_name")
	String sellerName;
	@Column
	String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String title) {
		this.model = title;
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

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getSpecificParams() {
		return specificParams;
	}

	public void setSpecificParams(String specific_params) {
		this.specificParams = specific_params;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate upload_date) {
		this.uploadDate = upload_date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String seller_name) {
		this.sellerName = seller_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
