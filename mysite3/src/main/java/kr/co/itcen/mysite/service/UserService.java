package kr.co.itcen.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.UserDao;
import kr.co.itcen.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}

	public UserVo getUser(UserVo vo) {
		
		return userDao.get(vo);
	}
	
	public void update(UserVo vo) {
		userDao.update(vo.getNo(), vo.getName(), vo.getGender(), vo.getPassword());
	}

	public boolean existUser(String email) {
		
		return userDao.get(email) != null;
	}

	public UserVo getUser(int no) {
		UserVo vo = new UserVo();
		vo.setNo(no);
		
		return userDao.get(vo);
	}
}
