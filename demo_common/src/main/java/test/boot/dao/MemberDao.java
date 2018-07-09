package test.boot.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import test.BaseMapper;
import test.boot.domain.Member;

public interface MemberDao extends BaseMapper<Member> {

	@Delete("DELETE FROM test_member WHERE id = #{id}")
	int deleteById(@Param("id") Integer id);

}
