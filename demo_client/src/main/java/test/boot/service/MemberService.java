package test.boot.service;

import java.util.List;

import test.boot.domain.Member;

/**
 * 成员操作接口
 * 
 * @author Administrator
 *
 */
public interface MemberService {

	/**
	 * 新增
	 * 
	 * @param member
	 *            成员信息
	 */
	int save(Member member);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);

	/**
	 * 修改
	 * 
	 * @param member
	 * @return
	 */
	int update(Member member);

	/**
	 * 查询成员信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Member> selectByPage(int pageNum, int pageSize);

	/**
	 * 查询所有成员信息
	 * 
	 * @return
	 */
	List<Member> selectAll();
}
