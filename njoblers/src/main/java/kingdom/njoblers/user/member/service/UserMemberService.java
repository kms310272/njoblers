package kingdom.njoblers.user.member.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import groovy.util.logging.Slf4j;
import kingdom.njoblers.user.member.repository.UserMemberMapper;


@Slf4j
@Service
public class UserMemberService {

	@Autowired
	UserMemberMapper memberMapper;
	private final JavaMailSender javaMailSender = null;

	public void signInMember(Map param) {
		memberMapper.insertMember(param);
	}

	public Map findByEmailAndPassword(Map param) {
		return memberMapper.findByEmailAndPassword(param);
	}

	public boolean isEmailExists(Map param) {

		int count = memberMapper.countByEmail(param);

		return count > 0 ? true : false;
	}

	public Map findbyMemberId(Map param) {
		return memberMapper.findById(param);
	}

	public void updateMember(Map param) {
		memberMapper.updateMember(param);
	}

	public void insertMemberHistory(Map param) {
		memberMapper.insertMemberHistory(param);
	}

	public void updateMemberStatus(Map param) {
		memberMapper.updateMemberStatus(param);
		
	}
	public void saveLoginHistory(Map<String, Object> loginHistory) {
		memberMapper.saveLoginHistory(loginHistory);	
	}
	public int getStatusById(Map param) {
		return memberMapper.getStatusById(param);
	}

	public String sendEmail(Map param) {
		
		Random random = new Random();
		String Authkey = "";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo((String) param.get("mail"));

		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(25) + 65; // A~Z까지 랜덤 알파벳 생성
			Authkey += (char) index;
		}
		
		int numIndex = random.nextInt(8999) + 1000; // 4자리 정수를 생성
		Authkey += numIndex;
		message.setSubject("Njoblers 인증번호 입니다.");

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 Njoblers 입니다.</h1>";
        msgg+= "<br>";
        msgg+= "<p>이메일 인증을 위해 아래 코드를 입력해주세요.<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>이메일 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= Authkey+"</strong><div><br/> ";
        msgg+= "</div>";
        
		message.setText(msgg);
		javaMailSender.send(message);

		return Authkey;
	}
}
