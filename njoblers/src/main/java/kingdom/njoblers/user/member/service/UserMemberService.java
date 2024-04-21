package kingdom.njoblers.user.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kingdom.njoblers.user.member.repository.UserMemberMapper;


@Service
public class UserMemberService {

	@Autowired
	UserMemberMapper memberMapper;

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

}
