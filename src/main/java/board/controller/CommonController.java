package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import board.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommonController {
	
	private final UserService userService;
	
	@GetMapping({"/"})
	public String mainPage() {
		return "/index";
	}
	
}
