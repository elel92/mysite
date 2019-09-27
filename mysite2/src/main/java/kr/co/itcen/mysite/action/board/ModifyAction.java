package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String para_no = request.getParameter("no");
		String para_user_no = request.getParameter("user_no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		int no = Integer.parseInt(para_no);
		int user_no = Integer.parseInt(para_user_no);
		
		new BoardDao().update(title, contents, no, user_no);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&no=" + no + "&user_no=" + user_no);
	}
}
