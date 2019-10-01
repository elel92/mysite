package kr.co.itcen.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.itcen.mysite.dto.JSonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void handlelrException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());
		//Logger.error(errors.toString());
		
		//2. 요청 구분
		// 만약 json 요청일 경우 : application/json
		// 만약 html 요청일 경우 : text/html
		// 만약 jpeg 요청일 경우 : image/jpeg
		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {
			//3. json 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			JSonResult jsonResult = JSonResult.fail(errors.toString());
			String result = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("utf-8"));
			os.close();
		} else {
			//4. 안내페이지 이동
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
