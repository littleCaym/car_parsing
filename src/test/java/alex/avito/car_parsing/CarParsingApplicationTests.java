package alex.avito.car_parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class CarParsingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void checkSpecificParamsParsingResult() {
		String str = "19 000 км, 1.6 MT (106 л.с.), универсал, передний, газ";
		String[] strings = str.split(",");
		System.out.println(strings[1]);
		System.out.println(Arrays.toString(strings[1].split("\\s")));
	}

	@Test
	void stringHasDecimal() {
		String locationString = "Крылатское 21–30 мин.";
		if (locationString.matches(".*\\d.*")) {
			locationString = "Москва";
		}
		assertEquals("Москва", locationString);
	}

}
