package alex.avito.car_parsing.contollers;

import alex.avito.car_parsing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatisticsController {

	private static final String CHART_TITLE_MODEL_ATTRIBUTE = "chartTitle";
	private static final String CHART_DATA_MODEL_ATTRIBUTE = "chartData";
	private static final String LINKS_MODEL_ATTRIBUTE = "links";
	private static final String STATISTICS_TEMPLATE = "statistics";

	private final CarService carService;

	@Autowired
	public StatisticsController(CarService carService) {
		this.carService = carService;
	}

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute(LINKS_MODEL_ATTRIBUTE, carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/statistics")
	public String getStatisticsForAllCars(Model model) {
		model.addAttribute(CHART_TITLE_MODEL_ATTRIBUTE, "All cars");
		model.addAttribute(CHART_DATA_MODEL_ATTRIBUTE, carService.getMiddlePriceForAllCarsGroupByUploadDate());
		return STATISTICS_TEMPLATE;
	}

	@GetMapping(value = "/model/{carModel}/statistics")
	public String getStatisticsForAllCars(Model model, @PathVariable String carModel) {
		model.addAttribute(CHART_TITLE_MODEL_ATTRIBUTE, carModel);
		model.addAttribute(CHART_DATA_MODEL_ATTRIBUTE, carService.getMiddlePriceForCarsByModelGroupByUploadDate(carModel));
		return STATISTICS_TEMPLATE;
	}

}
