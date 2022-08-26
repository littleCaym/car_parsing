package alex.avito.car_parsing.contollers;

import alex.avito.car_parsing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/model")
public class CarModelController {

	private static final String CAR_LIST_MODEL_ATTRIBUTE = "carlist";
	private static final String LINKS_MODEL_ATTRIBUTE = "links";
	private static final String CAR_ACTUAL_MODEL_ATTRIBUTE = "caractual";
	private static final String CAR_MODEL_TEMPLATE = "carmodel";

	private final CarService carService;

	@Autowired
	public CarModelController(CarService carService) {
		this.carService = carService;
	}

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute(LINKS_MODEL_ATTRIBUTE, carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/{carModel}")
	public String getCarsByModel(Model model, @PathVariable String carModel) {
		carModel = carModel.replaceAll("\\s", " ");
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getCarsByModelOrderByModelAsc(carModel));
		model.addAttribute(CAR_ACTUAL_MODEL_ATTRIBUTE, carModel);
		return CAR_MODEL_TEMPLATE;
	}

	@GetMapping(value = "/{carModel}/byYearAsc")
	public String getAllCarsByOrderByYearAsc(Model model, @PathVariable String carModel) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getCarsByModelOrderByYearAsc(carModel));
		model.addAttribute(CAR_ACTUAL_MODEL_ATTRIBUTE, carModel);
		return CAR_MODEL_TEMPLATE;
	}

	@GetMapping(value = "/{carModel}/byPriceAsc")
	public String getAllCarsByOrderByPriceAsc(Model model, @PathVariable String carModel) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getCarsByModelOrderByPriceAsc(carModel));
		model.addAttribute(CAR_ACTUAL_MODEL_ATTRIBUTE, carModel);
		return CAR_MODEL_TEMPLATE;
	}

	@GetMapping(value = "/{carModel}/byUploadDateAsc")
	public String getAllCarsByUploadDateAsc(Model model, @PathVariable String carModel) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getCarsByModelOrderByUploadDateAsc(carModel));
		model.addAttribute(CAR_ACTUAL_MODEL_ATTRIBUTE, carModel);
		return CAR_MODEL_TEMPLATE;
	}

	@GetMapping(value = "/{carModel}/byLocationAscStartWithMoscow")
	public String getAllCarsOrderByLocationAscStartWithMoscow(Model model, @PathVariable String carModel) {
		model.addAttribute(CAR_LIST_MODEL_ATTRIBUTE, carService.getCarsByModelOrderByLocationAscStartWithMoscow(carModel));
		model.addAttribute(CAR_ACTUAL_MODEL_ATTRIBUTE, carModel);
		return CAR_MODEL_TEMPLATE;
	}

}
