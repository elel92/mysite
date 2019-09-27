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

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kwd = request.getParameter("kwd");
		String page_no_ = request.getParameter("page_no");
		String next_page_count_ = request.getParameter("next_page_count");
		
		int page_no = 0;
		int next_page_count = 1;
		
		if(page_no_ != null) {
			page_no = Integer.parseInt(page_no_);
		}
		
		if(next_page_count_ != null) {
			next_page_count = Integer.parseInt(next_page_count_);
		}
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?page_no="+page_no+"&kwd="+kwd+"&next_page_count="+next_page_count);
	}
}
