package alex.avito.car_parsing.contollers;

import alex.avito.car_parsing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainCarController {

	private final CarService carService;

	@Autowired
	public MainCarController(CarService carService) {
		this.carService = carService;
	}

	@ModelAttribute
	public void preLoad(Model model) {
		model.addAttribute("links", carService.getAllLinksFromDb());
	}

	@GetMapping(value = "/")
	public String getAllCarsByOrderByModelAsc(Model model) {
		model.addAttribute("carlist", carService.getAllCarsByOrderByModelAsc());
		return "index";
	}


	@GetMapping(value = "/byYearAsc")
	public String getAllCarsByOrderByYearAsc(Model model) {
		model.addAttribute("carlist", carService.getAllCarsByOrderByYearAsc());
		return "index";
	}

	@GetMapping(value = "/byPriceAsc")
	public String getAllCarsByOrderByPriceAsc(Model model) {
		model.addAttribute("carlist", carService.getAllCarsByOrderByPriceAsc());
		return "index";
	}

	@GetMapping(value = "/byUploadDateAsc")
	public String getAllCarsByUploadDateAsc(Model model) {
		model.addAttribute("carlist", carService.getAllCarsByOrderByUploadDateAsc());
		return "index";
	}

}
