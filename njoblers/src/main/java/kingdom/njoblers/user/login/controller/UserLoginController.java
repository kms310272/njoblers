package kingdom.njoblers.user.login.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kingdom.njoblers.user.member.service.UserMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class UserLoginController {

	@Autowired
	public UserMemberService memberService;

	/* 로그인페이지 */
	@GetMapping
    public String viewLogin(Model model){

        return "user/login/login";
    }

	/* member 확인 & 로그인 정보 저장 */	
	@PostMapping("/authenticate")
    public String authenticate(HttpServletRequest request, @RequestBody Map param, Model model) {
		
		Map foundMember = memberService.findByEmailAndPassword(param);
	    
	    // 로그인 처리 로직
	    if (foundMember != null) {
	        // 로그인 성공 시 로그인 이력 저장
	        Map<String, Object> loginHistory = new HashMap<>();
	        Integer memberSn = (Integer) foundMember.get("member_sn");
	        
	        loginHistory.put("member_sn", memberSn);
	        loginHistory.put("login_time", LocalDateTime.now());
	        loginHistory.put("logout_time", null);
	        loginHistory.put("ip", request.getRemoteAddr());
	        loginHistory.put("browser_info", request.getHeader("User-Agent"));
	        loginHistory.put("session_id", request.getSession().getId());
	        
	        memberService.saveLoginHistory(loginHistory);

	        // 세션에 사용자 정보 저장
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", memberSn);
	        return "redirect:/"; 
	    } else {
	        model.addAttribute("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
	        return "user/login/login";
	    }
	    
    }
	
	@PostMapping("/CheckMail")
	public String SendMail(HttpServletRequest request, @RequestBody Map param, Model model) {
		 String Authkey = memberService.sendEmail(param);
		 return "코드 발급" + Authkey;
	}
}