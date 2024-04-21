package kingdom.njoblers.admin.member.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMemberMapper {
    Map findByEmailAndPassword(Map param);
	int countByEmail(Map param);
	int insertMember(Map param);
}

