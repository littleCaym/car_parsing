package alex.avito.car_parsing.contollers;

import alex.avito.car_parsing.services.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticsController {

	@Autowired
	CarService carService;

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute("links", carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/statistics")
	public String getStatisticsForAllCars(Model model) {
		List<List<Object>> data = carService.getMiddlePriceForAllCarsGroupBySession();
		model.addAttribute("chartTitle", "All cars");
		model.addAttribute("chartData", carService.getMiddlePriceForAllCarsGroupBySession());
		return "statistics";
	}

	@GetMapping(value = "/model/{carModel}/statistics")
	public String getStatisticsForAllCars(Model model, @PathVariable String carModel) {
		List<List<Object>> data = carService.getMiddlePriceForCarsByModelGroupBySession(carModel);
		model.addAttribute("chartTitle", carModel);
		model.addAttribute("chartData", carService.getMiddlePriceForCarsByModelGroupBySession(carModel));
		return "statistics";
	}

}
