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

	@Autowired
	CarService carService;

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute("links", carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/{carModel}")
	public String getCarsByModel(Model model, @PathVariable String carModel) {
		carModel = carModel.replaceAll("\\s", " ");
		model.addAttribute("carlist", carService.getCarsByModelOrderByModelAsc(carModel));
		model.addAttribute("caractual", carModel);
		return "carmodel";
	}

	@GetMapping(value = "/{carModel}/byYearAsc")
	public String getAllCarsByOrderByYearAsc(Model model, @PathVariable String carModel) {
		model.addAttribute("carlist", carService.getCarsByModelOrderByYearAsc(carModel));
		model.addAttribute("caractual", carModel);
		return "carmodel";
	}

	@GetMapping(value = "/{carModel}/byPriceAsc")
	public String getAllCarsByOrderByPriceAsc(Model model, @PathVariable String carModel) {
		model.addAttribute("carlist", carService.getCarsByModelOrderByPriceAsc(carModel));
		model.addAttribute("caractual", carModel);
		return "carmodel";
	}

	@GetMapping(value = "/{carModel}/byUploadDateAsc")
	public String getAllCarsByUploadDateAsc(Model model, @PathVariable String carModel) {
		model.addAttribute("carlist", carService.getCarsByModelOrderByUploadDateAsc(carModel));
		model.addAttribute("caractual", carModel);
		return "carmodel";
	}


}
