package alex.avito.car_parsing.contollers;

import alex.avito.car_parsing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainCarController {

	private static final String CAR_LIST_MODEL_ATTRIBUTE = "carlist";
	private static final String LINKS_MODEL_ATTRIBUTE = "links";
	private static final String INDEX_TEMPLATE = "index";

	private final CarService carService;

	@Autowired
	public MainCarController(CarService carService) {
		this.carService = carService;
	}

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute(LINKS_MODEL_ATTRIBUTE, carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/")
	public String getAllCarsByOrderByModelAsc(Model model) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getAllCarsByOrderByModelAsc());
		return INDEX_TEMPLATE;
	}

	@GetMapping(value = "/byYearAsc")
	public String getAllCarsByOrderByYearAsc(Model model) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getAllCarsByOrderByYearAsc());
		return INDEX_TEMPLATE;
	}

	@GetMapping(value = "/byPriceAsc")
	public String getAllCarsByOrderByPriceAsc(Model model) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getAllCarsByOrderByPriceAsc());
		return INDEX_TEMPLATE;
	}

	@GetMapping(value = "/byUploadDateAsc")
	public String getAllCarsByUploadDateAsc(Model model) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getAllCarsByOrderByUploadDateAsc());
		return INDEX_TEMPLATE;
	}

	@GetMapping(value = "/byLocationAscStartWithMoscow")
	public String getAllCarsOrderByLocationAscStartWithMoscow(Model model) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getAllCarsOrderByLocationAscStartWithMoscow());
		return INDEX_TEMPLATE;
	}

}
