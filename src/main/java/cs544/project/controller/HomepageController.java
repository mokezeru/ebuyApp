package cs544.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

	@GetMapping({"/home", "/","/index"})
	public String homepage() {
		return "homepage/index";
	}
}
