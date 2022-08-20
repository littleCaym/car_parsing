package alex.avito.car_parsing.tasks;

import alex.avito.car_parsing.models.Car;
import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.services.CarService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//Lancer:

@Component
public class ParsingTask {

	Logger LOG = Logger.getLogger(ParsingTask.class.getName());

	@Autowired
	CarService carService;

	LocalDate localDateCurr;


	//TODO:	@Scheduled(cron = "* 0 9/6 * * *")
	//todo загрузка должна быть раз в 24 часа
	@Scheduled(initialDelay = 1000, fixedDelay=Long.MAX_VALUE)
	public void parsingCars() throws InterruptedException {

		localDateCurr = LocalDate.now();

		if (!parsingIsToEarly(localDateCurr)) {
			List<Link> linkList = carService.getAllLinksFromDb();

			for (Link link :
					linkList) {

				try {
					Document doc = Jsoup.connect(link.getLink())
							.userAgent("Mozilla")
							.timeout(10000) //TODO: check!
							.get();

					String strHtml = doc.outerHtml();
					doc = Parser.parse(strHtml, link.getLink());
					Elements content = doc.getElementsByClass("iva-item-content-rejJg");
					LOG.info(link.getDescription() + " parsing started");

//					contentRestructuring(content);


				} catch (IOException e) {
					e.printStackTrace();
					//TODO: А если АВИТО не ответил?
				}

				Thread.sleep(60000);

			}
		}
	}

	private void contentRestructuring(Elements content) {

		for (Element e : content) {
			Car car = new Car();

			String strDateUpload = e.select("div[class=iva-item-dateInfoStep-_acjp]").text();
			if (!(strDateUpload.contains("час") || strDateUpload.contains("мин"))) {
				continue;
			}
			car.setUploadDate(localDateCurr);


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

			car.setSellerName(e.
					select("div[class=style-title-_wK5H text-text-LurtD text-size-s-BxGpL]")
					.text());

			car.setSpecificParams(e.
					select("div[class=iva-item-descriptionStep-C0ty1]").text());

			car.setLocation(e.
					select("div[class=geo-root-zPwRk iva-item-geo-_Owyg]").text());

			car.setLink("https://www.avito.ru/"+
					e.getElementsByTag("a").first().attr("href")
			);

			car.setImageSrc(e
					.getElementsByClass("photo-slider-image-YqMGj")
					.attr("src")
			);

			carService.saveCar(car);

		}
	}

	private boolean parsingIsToEarly(LocalDate currDate) {
		return carService
				.checkLastUploadDateEqualsDate(currDate);
	}

}
