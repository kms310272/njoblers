package kingdom.njoblers.admin.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kingdom.njoblers.admin.member.service.AdminMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin-member")
public class AdminMemberController {

	@Autowired
	public AdminMemberService adminMemberService;

	/* 회원가입 */
	@PostMapping("/signin")
	public String signIn(@RequestBody Map param, Model model){

	    if (adminMemberService.isEmailExists(param)) {
	        model.addAttribute("error", "이미 가입된 이메일 입니다.");
	        return "redirect:/signin";
	    }

	    adminMemberService.signInMember(param);

	    return "redirect:/login";

	}


}