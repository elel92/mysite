package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		UserVo pre_userVo = (UserVo)session.getAttribute("authUser");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		new UserDao().update(pre_userVo.getNo(), name, gender, password);
		
		UserVo userVo = new UserVo();
		userVo.setNo(pre_userVo.getNo());
		userVo.setName(name);
		userVo.setEmail(pre_userVo.getEmail());
		userVo.setGender(gender);
		
		session.setAttribute("authUser", userVo);
		
		WebUtils.redirect(request, response, request.getContextPath());
	}
}
