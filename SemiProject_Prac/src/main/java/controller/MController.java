package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Mail;
import dao.MemberDAO;
import dto.Member;


@WebServlet("*.do")
public class MController extends HttpServlet {
	
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
	MemberDAO md = MemberDAO.getInstance();
	
	String url = request.getRequestURI();
	String ctx = request.getContextPath();
	String uri = url.substring(ctx.length());
	switch (uri) {
	case "/Kakaosign.do": 
		try {
			if(md.dupliId(request.getParameter("email"))) {
				request.getSession().setAttribute("email",request.getParameter("email"));
			}else {
				request.setAttribute("Kakao_email",request.getParameter("email"));
				request.setAttribute("Kakao_nickname",request.getParameter("nickname"));
				request.setAttribute("snscheck",request.getParameter("snsCheck"));
				request.getRequestDispatcher("signForm.jsp").forward(request, response);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		
	case "/login.do":
		try {
			if(md.login(new Member(request.getParameter("id"),request.getParameter("pw")))) {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/auth.do":
		Mail.confirmNumber(request.getParameter("email"));
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("contact1")+"-"+request.getParameter("contact2")+"-"+request.getParameter("contact3");
		String snscheck = !(request.getParameter("snscheck").equalsIgnoreCase(""))?request.getParameter("snscheck") :"0";
		try {
			md.insert(new Member(email,pw,nickname,phone,snscheck));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
			
	
	}
	
	request.setAttribute("Kakao_email",request.getParameter("email"));
	request.setAttribute("Kakao_nickname",request.getParameter("nickname"));
		
}

}
