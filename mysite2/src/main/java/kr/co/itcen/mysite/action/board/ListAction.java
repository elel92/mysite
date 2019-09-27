package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page_no_ = request.getParameter("page_no");
		String kwd = request.getParameter("kwd");
		String next_page_count_ = request.getParameter("next_page_count");
		
		int page_no = 0;
		int next_page_count = 1;
		
		if(next_page_count_ != null) {
			next_page_count = Integer.parseInt(next_page_count_);
		}
		
		if(page_no_ != null) {
			page_no = Integer.parseInt(page_no_);
		}
		
		if(kwd != null) {
			request.setAttribute("kwd", kwd);
			
			List<BoardVo> page_list = new BoardDao().searchList_page(kwd, page_no);
			List<BoardVo> list = new BoardDao().searchList(kwd);
			
			request.setAttribute("page_list", page_list);
			request.setAttribute("page_num", page_no+1);
			request.setAttribute("list", list);
			request.setAttribute("next_page_count", next_page_count);
		} else {
			List<BoardVo> page_list = new BoardDao().limit_getList(page_no);
			List<BoardVo> list = new BoardDao().getList();
			
			request.setAttribute("page_list", page_list);
			request.setAttribute("page_num", page_no+1);
			request.setAttribute("list", list);
			request.setAttribute("next_page_count", next_page_count);
		}
		
		WebUtils.forward(request, response, "WEB-INF/views/board/list.jsp");
	}
}
