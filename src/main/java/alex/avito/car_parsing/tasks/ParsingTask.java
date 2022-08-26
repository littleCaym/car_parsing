package alex.avito.car_parsing.tasks;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.models.Session;
import alex.avito.car_parsing.services.CarService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParsingTask {

	Logger LOG = Logger.getLogger(ParsingTask.class.getName());

	private final CarService carService;

	@Autowired
	public ParsingTask(CarService carService) {
		this.carService = carService;
	}

	LocalDate localDateCurr;

  //грузим страницу по дню
	@Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
	public void parsingCars() throws InterruptedException {

		localDateCurr = LocalDate.now();

		List<Link> linkList = carService.getAllLinksFromDb();

		List<Car> existingCarList = carService.getAllCarsByOrderByUploadDateDesc();

		for (Link link :
				linkList) {

			try {
				Document doc = Jsoup
						.connect(link.getLink())
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.63")
						.timeout(20000)
						.get();

				String strHtml = doc.outerHtml();
				doc = Parser.parse(strHtml, link.getLink());
				Elements content = doc.getElementsByClass("iva-item-content-rejJg");
				LOG.info(link.getDescription() + " parsing started");

				Session session = new Session();
				session.setTimestamp(new Timestamp(System.currentTimeMillis()));
				carService.saveSession(session);

				contentRestructuring(content, session, existingCarList);


			} catch (IOException e) {
				e.printStackTrace();
			}

			LOG.info("Waiting before next search...");
			Thread.sleep(30000);

		}

		LOG.info("Parsing is finished.");
	}

	private void contentRestructuring(Elements content, Session currSession, List<Car> existingCarList) {

		for (Element e : content) {
			Car car = new Car();

			String strDateUpload = e.select("div[class=iva-item-dateInfoStep-_acjp]").text();
			if (!(strDateUpload.contains("час") || strDateUpload.contains("мин"))) {
				continue;
			}
			car.setUploadDate(localDateCurr);

			car.setLink("https://www.avito.ru/"+
					Objects.requireNonNull(e.getElementsByTag("a").first()).attr("href")
			);

			if (existingCarList.stream()
					.anyMatch(carEx ->
							carEx.getLink().equals(car.getLink())
					)) {
					continue;
			}

			String st = e.select("div[class=iva-item-priceStep-uq2CQ]").text();
			st = st.substring(0, st.length()-2);
			st = st.replaceAll("\\s+","");
			try{
				car.setPrice(Integer.parseInt(st));
			} catch (Exception exception){
				continue;
			}

			String[] titleAndYear = e.select("div[class=iva-item-titleStep-pdebR]")
					.text().split(", ");

			car.setModel(titleAndYear[0]);

			car.setYear(Short.parseShort(titleAndYear[1]));

			List<Object> specificParams = parseSpecificParams(
					e.getElementsByClass("iva-item-text-Ge6dR text-text-LurtD text-size-s-BxGpL").text());

			System.out.println(specificParams);

			car.setMileage((int) specificParams.get(0));

			car.setEngineCapacity((float) specificParams.get(1));

			car.setTransmissionType((String) specificParams.get(2));

			car.setHorsePower((int) specificParams.get(3));

			car.setBodyStyle((String) specificParams.get(4));

			car.setWheelDriveType((String) specificParams.get(5));

			car.setFuelType((String) specificParams.get(6));

			car.setSellerName(e.
					select("div[class=style-title-_wK5H text-text-LurtD text-size-s-BxGpL]").text());

			car.setDescription(e.
					select("div[class=iva-item-descriptionStep-C0ty1]").text());

			String locationString = e.
					select("div[class=geo-root-zPwRk iva-item-geo-_Owyg]").text();
			if (locationString.matches(".*\\d.*")) {
				locationString = "Москва";
			}
			car.setLocation(locationString);

			car.setImageSrc(e
					.getElementsByClass("photo-slider-image-YqMGj").attr("src"));

			car.setSession(currSession);

			Link searchLink = carService.getLinkByDescription(car.getModel());
			car.setSearchLink(searchLink);

			carService.saveCar(car);
			LOG.info(car.getModel()+ " is saved");

		}
	}

	private List<Object> parseSpecificParams(String unifiedString) {
		List<Object> objectList = new ArrayList<>();
		if (unifiedString.contains(",")) {
			String[] dividedStrings = unifiedString.split(", ");

			if (dividedStrings[0].contains("км")){
				objectList.add(
						Integer.parseInt(
								dividedStrings[0]
										.substring(0, dividedStrings[0].length()-3)
												.replaceAll("\\s","")
						));
			} else {
				objectList.add(0);
			}

			String[] capTransHorseStrings = dividedStrings[1].split("\\s");

			objectList.add(Float.parseFloat(
					BigDecimal.valueOf(Float.parseFloat(capTransHorseStrings[0]))
							.setScale(1, RoundingMode.CEILING).toString()
			));
			objectList.add(capTransHorseStrings[1]);
			objectList.add(Integer.parseInt(capTransHorseStrings[2].split(",")[0].substring(1)));

			objectList.add(dividedStrings[2]);
			objectList.add(dividedStrings[3]);
			objectList.add(dividedStrings[4]);

		}
		return objectList;
	}
}
