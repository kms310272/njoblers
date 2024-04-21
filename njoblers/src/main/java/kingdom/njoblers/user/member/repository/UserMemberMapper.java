package kingdom.njoblers.user.member.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMemberMapper {
    Map findByEmailAndPassword(Map param);
	int countByEmail(Map param);
	int insertMember(Map param);
	Map findById(Map param);
	void updateMember(Map param);
	void insertMemberHistory(Map param);
	void updateMemberStatus(Map param);
	void saveLoginHistory(Map<String, Object> loginHistory);
	int getStatusById(Map param);
}

