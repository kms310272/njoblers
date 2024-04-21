package kingdom.njoblers.admin.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kingdom.njoblers.admin.member.repository.AdminMemberMapper;


@Service
public class AdminMemberService {

	@Autowired
	private AdminMemberMapper adminMemberMapper;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public void signInMember(Map param) {

//		String rawPassword = (String) param.get("pw");
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//        param.put("pw", encodedPassword);

		adminMemberMapper.insertMember(param);
	}

	public Map findByEmailAndPassword(Map param) {
		return adminMemberMapper.findByEmailAndPassword(param);
	}


	public boolean isEmailExists(Map param) {

		int count = adminMemberMapper.countByEmail(param);

		return count > 0 ? true : false;
	}

}