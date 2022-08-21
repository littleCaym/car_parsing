package alex.avito.car_parsing.tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParsingTaskTest {

	@Test
	void parsingCars() {
	}

	@Test
	void parseSpecificParams() {

		String unifiedString = "119 000 км, 1.6 MT (106 л.с.), универсал, передний, газ";


		List<Object> objectList = new ArrayList<>();
		if (unifiedString.contains(",")) {
			String[] dividedStrings = unifiedString.split(", ");

			if (dividedStrings[0].contains("км")) {
				objectList.add(
						Integer.parseInt(
								dividedStrings[0]
										.substring(0, dividedStrings[0].length() - 3)
										.replaceAll("\\s", "")
						));
			} else {
				objectList.add(0);
			}

			String[] capTransHorseStrings = dividedStrings[1].split("\\s");

			objectList.add(Float.parseFloat(capTransHorseStrings[0]));
			objectList.add(capTransHorseStrings[1]);
			objectList.add(capTransHorseStrings[2].split(",")[0].substring(1));

			objectList.add(dividedStrings[2]);
			objectList.add(dividedStrings[3]);
			objectList.add(dividedStrings[4]);

			assertEquals("[119000, 1.6, MT, 106, универсал, передний, газ]", objectList.toString());
		}
	}

}