package kingdom.njoblers.user.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kingdom.njoblers.user.member.service.UserMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class UserMemberController {

 	@Autowired
	public UserMemberService memberService;

	/* 회원가입 */
	@PostMapping("/signin")
	public String signIn(@RequestBody Map param, Model model){

	    if (memberService.isEmailExists(param)) {
	        model.addAttribute("error", "이미 가입된 이메일 입니다.");
	        return "redirect:/signin";
	    }

	    memberService.signInMember(param);

	    return "redirect:/login";

	}
	
	/* 회원 정보 수정 */
	@PostMapping("/update")
	public String updateMember(@RequestBody Map param, Model model){

		Map foundMember = memberService.findbyMemberId(param);
		
	    if (foundMember != null) {
	    	memberService.insertMemberHistory(param);
	    	
	    	if (param.containsKey("pw") && param.get("pw") != null) {
	    		memberService.updateMember(param);
	        }
	        model.addAttribute("success", "수정 되었습니다.");
	        return "redirect:/";
	    }

	    model.addAttribute("error", "다시 확인 해주세요.");
        return "redirect:/";

	}
	
	/* 회원 상태 변경 */
	@PostMapping("/update/status")
	public String updateMemberStatus(@RequestBody Map param, Model model){

		 Map foundMember = memberService.findbyMemberId(param);
		    
		    if (foundMember != null) {
		        memberService.insertMemberHistory(param);

		        if (param.containsKey("pw") && param.get("pw") != null) {
		            memberService.updateMemberStatus(param);
		        }
		        
		        int updatedStatus = memberService.getStatusById(param);
		        
		        if (updatedStatus == 0) {
		            model.addAttribute("success", "탈퇴 되었습니다.");
		        }
		        
		        return "redirect:/";
		    }

		    model.addAttribute("error", "다시 확인 해주세요.");
		    return "redirect:/";

	}
	

}