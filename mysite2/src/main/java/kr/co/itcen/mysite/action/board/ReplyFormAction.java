package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String para_no = request.getParameter("no");
		String para_user_no = request.getParameter("user_no");
		
		int no = Integer.parseInt(para_no);
		int user_no = Integer.parseInt(para_user_no);
		
		BoardVo vo = new BoardDao().reply_select(no, user_no);
		request.setAttribute("reply_vo", vo);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/write.jsp");
	}
}
