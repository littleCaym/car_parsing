package alex.avito.car_parsing.tasks;

import alex.avito.car_parsing.models.Link;
import alex.avito.car_parsing.services.CarService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class startAppTask {

	@Autowired
	CarService carService;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		createLinkList();
	}

	private void createLinkList() {
		List<Link> linkList = carService.getAllLinksFromDb();

		if (!linkList.isEmpty()){
			carService.deleteAllLinks();
		}

		linkList.add(new Link(1,
				"https://www.avito.ru/moskva/avtomobili/mitsubishi/lancer/x_restayling-ASgBAgICA0Tgtg3ymCjitg3wqCjqtg3u2Sg?cd=1&f=ASgBAQICBETyCrCKAeC2DfKYKOK2DfCoKOq2De7ZKAFA9sQNFL6wOg&radius=0&s=104",
				"Mitsubishi Lancer"
		));
		linkList.add(new Link(2,
				"https://www.avito.ru/moskva/avtomobili/honda/accord-ASgBAgICAkTgtg2ymCjitg30nSg?cd=1&f=ASgBAQICA0TyCrCKAeC2DbKYKOK2DfSdKAFA9sQNFL6wOg&radius=0&s=104",
				"Honda Accord"
		));
		linkList.add(new Link(3,
				"https://www.avito.ru/moskva/avtomobili/acura/rsx-ASgBAgICAkTgtg3Slyjitg34rig?cd=1&f=ASgBAQICA0TyCrCKAeC2DdKXKOK2DfiuKAFA9sQNFL6wOg&radius=0&s=104",
				"Acura RSX"
		));
		linkList.add(new Link(4,
				"https://www.avito.ru/moskva/avtomobili/mazda/mx-5-ASgBAgICAkTgtg3mmCjitg3Gqyg?cd=1&f=ASgBAQICA0TyCrCKAeC2DeaYKOK2DcarKAFA9sQNFL6wOg&radius=0&s=104",
				"Mazda MX-5"
		));
		linkList.add(new Link(5,
				"https://www.avito.ru/moskva/avtomobili/mazda/rx-8-ASgBAgICAkTgtg3mmCjitg2Cryg?cd=1&f=ASgBAQICA0TyCrCKAeC2DeaYKOK2DYKvKAFA9sQNFL6wOg&radius=0&s=104",
				"Mazda RX-8"
		));
		linkList.add(new Link(6,
				"https://www.avito.ru/moskva/avtomobili/subaru/impreza-ASgBAgICAkTgtg2mmSjitg3qpyg?cd=1&f=ASgBAQICA0TyCrCKAeC2DaaZKOK2DeqnKAFA9sQNFL6wOg&radius=0&s=104",
				"Subaru Impreza"
		));
		linkList.add(new Link(7,
				"https://www.avito.ru/moskva/avtomobili/subaru/legacy-ASgBAgICAkTgtg2mmSjitg2cqSg?cd=1&f=ASgBAQICA0TyCrCKAeC2DaaZKOK2DZypKAFA9sQNFL6wOg&radius=0&s=104",
				"Subaru Legacy"
		));
		linkList.add(new Link(8,
				"https://www.avito.ru/moskva/avtomobili/porsche/cayenne-ASgBAgICAkTgtg2GmSjitg2AoSg?cd=1&f=ASgBAQICA0TyCrCKAeC2DYaZKOK2DYChKAFA9sQNFL6wOg&radius=0&s=104",
				"Porsche Cayenne"
		));
		linkList.add(new Link(9,
				"https://www.avito.ru/moskva/avtomobili/s_probegom/lexus/es-ASgBAgICA0SGFMjmAeC2DdaYKOK2DbKkKA?f=ASgBAQICBETyCrCKAYYUyOYB4LYN1pgo4rYNsqQoAUD2xA0UvrA6&radius=0&s=104",
				"Lexus ES"
		));
		linkList.add(new Link(10,
				"https://www.avito.ru/moskva/avtomobili/lexus/ls-ASgBAgICAkTgtg3WmCjitg3cqSg?cd=1&f=ASgBAQICA0TyCrCKAeC2DdaYKOK2DdypKAFA9sQNFL6wOg&radius=0&s=104",
				"Lexus LS"
		));

		carService.saveAllLinks(linkList);

	}

}
