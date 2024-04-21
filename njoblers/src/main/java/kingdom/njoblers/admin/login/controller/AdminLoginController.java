package kingdom.njoblers.admin.login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kingdom.njoblers.admin.member.service.AdminMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin-login")
public class AdminLoginController {

	@Autowired
	public AdminMemberService memberService;

	/* 로그인페이지 */
	@GetMapping
    public String viewLogin(Model model){

        return "admin/login/login";
    }

	/* member 확인 */
	@PostMapping("/authenticate")
	public String authenticate(@RequestBody Map param, Model model){

	    Map foundMember = memberService.findByEmailAndPassword(param);

	    if(foundMember != null) {
	        return "redirect:/";
	    } else {
	        model.addAttribute("error", "이메일, 비밀번호를 확인해 주세요.");
	        return "admin/login/login";
	    }
	}
}
