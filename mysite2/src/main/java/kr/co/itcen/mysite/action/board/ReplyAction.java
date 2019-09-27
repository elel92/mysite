package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.mvc.Action;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String para_no = request.getParameter("no");
		String para_g_no = request.getParameter("g_no");
		String para_o_no = request.getParameter("o_no");
		String para_depth = request.getParameter("depth");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		int no = Integer.parseInt(para_no);
		int g_no = Integer.parseInt(para_g_no);
		int o_no = Integer.parseInt(para_o_no);
		int depth = Integer.parseInt(para_depth);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setUser_no(userVo.getNo());
		boardVo.setG_no(g_no);
		boardVo.setO_no(o_no);
		boardVo.setDepth(depth);
		
		new BoardDao().reply_insert(boardVo);
		
		response.sendRedirect(request.getContextPath() + "/board");
	}
}
