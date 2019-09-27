package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String para_no = request.getParameter("no");
		String para_user_no = request.getParameter("user_no");
		
		int no = Integer.parseInt(para_no);
		int user_no = Integer.parseInt(para_user_no);
		
		BoardVo boardVo = new BoardDao().select(no, user_no);
		boardVo.setUser_no(user_no);
		boardVo.setNo(no);
		request.setAttribute("read_list", boardVo);
		
		new BoardDao().update_hit(no, user_no);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/view.jsp");
	}

}
