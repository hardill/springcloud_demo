package test.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import test.boot.dao.MemberDao;
import test.boot.domain.Member;
import test.boot.service.MemberService;

/**
 * 成员接口实现类
 * 
 * @author Administrator
 *
 */
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	@Transactional
	public int save(Member member) {
		return memberDao.insert(member);
	}

	@Override
	@Transactional
	public int deleteById(Integer id) {
		return memberDao.deleteById(id);
	}

	@Override
	@Transactional
	public int update(Member member) {
		return memberDao.updateByPrimaryKeySelective(member);
	}

	@Override
	public List<Member> selectByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return memberDao.selectAll();
	}

	@Override
	public List<Member> selectAll() {
		return memberDao.selectAll();
	}

}
