package kr.co.itcen.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1. handler 종류(DefaultServletHttpRequestHandler, HandlerMethod)
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. @Auth가 없을 때
		if(auth == null) {
			if(handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class) != null) {
				auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
			}
		}
		
		//5. @Auth가 없을 때
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 class나 method에 붙어 있기 때문에 인증 여부 체크
		HttpSession session = request.getSession();
		
		if(session == null || session.getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. Role 가져오기
		String role = auth.value();
		
		//8. 메소드의 @Auth의 Role이 "USER"인 경우, 인증만 되어 있으면 모두 통과
		if("USER".equals(role)) {
			return true;
		}
		
		//9. 메소드의 @Auth의 Role이 "ADMIN"인 경우,
		if("ADMIN".equals(role)) {
			UserVo userVo = (UserVo)session.getAttribute("authUser");
			
			if(userVo.getName().equals("관리자")) {
				
				return true;
			}
			
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return false;
	}
	
}
