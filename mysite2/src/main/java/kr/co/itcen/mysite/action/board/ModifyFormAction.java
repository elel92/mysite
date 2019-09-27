package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String para_no = request.getParameter("no");
		String para_user_no = request.getParameter("user_no");
		
		int no = Integer.parseInt(para_no);
		int user_no = Integer.parseInt(para_user_no);
		
		BoardVo boardVo = new BoardDao().select(no, user_no);
		boardVo.setUser_no(user_no);
		boardVo.setNo(no);
		request.setAttribute("update_list", boardVo);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/modify.jsp");
	}
	
}
