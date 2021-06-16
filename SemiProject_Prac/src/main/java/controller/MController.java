package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import Util.Mail;
import dao.BoardDAO;
import dao.MemberDAO;
import dto.Board;
import dto.Member;


@WebServlet("*.do")
public class MController extends HttpServlet {
	
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Gson gs = new Gson(); 
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
	MemberDAO md = MemberDAO.getInstance();
	BoardDAO bd = BoardDAO.getInstance();
	String url = request.getRequestURI();
	String ctx = request.getContextPath();
	String uri = url.substring(ctx.length());
	System.out.println(uri);
	switch (uri) {
	case "/Kakaosign.do": 
		try {
			System.out.println(md.dupliId(request.getParameter("email")));
			if(md.dupliId(request.getParameter("email"))) {
				request.getSession().setAttribute("email",request.getParameter("email"));
				response.sendRedirect(ctx+"/MainFrame.do");
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
				request.getSession().setAttribute("email",request.getParameter("id"));
				response.sendRedirect(ctx+"/MainFrame.do");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/auth.do":
		System.out.println(request.getParameter("email"));
		Mail.confirmNumber(request.getParameter("email"));
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("contact1")+"-"+request.getParameter("contact2")+"-"+request.getParameter("contact3");
		String snscheck = !(request.getParameter("snscheck").equalsIgnoreCase(""))?request.getParameter("snscheck") :"0";
		request.setAttribute("email", email);
		request.setAttribute("password",pw);
		request.setAttribute("nickname",nickname);
		request.setAttribute("phone",phone );
		request.setAttribute("snscheck",snscheck);
		request.getRequestDispatcher("EmailAuth.jsp").forward(request, response);
		break;
	case "/signup.do": 
		if(Mail.checkNumber(Integer.parseInt(request.getParameter("number")))) {
			String sign_email = request.getParameter("email");
			String sign_pw = request.getParameter("password");
			String sign_nickname = request.getParameter("nickname");
			String sign_phone = request.getParameter("phone");
			String sign_snscheck = !(request.getParameter("snscheck").equalsIgnoreCase(""))?request.getParameter("snscheck") :"0";
			try {
				md.insert(new Member(sign_email, sign_pw, sign_phone, sign_nickname, sign_snscheck));
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		break;
	case "/MainFrame.do": 
		try {
			List<Board> _default = bd.likeFacebook(5,1);
			System.out.println(_default.size());
			request.setAttribute("defalut", _default);
			request.getRequestDispatcher("MainFrame.jsp").forward(request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case "/sendlist.do": 
		try {
			int count = Integer.parseInt(request.getParameter("count"));
			System.out.println(count);
			List<Board> temp = bd.likeFacebook(5,count);
			System.out.println(temp.size());
			String result = gs.toJson(temp);
			response.getWriter().append(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		break;
		
	case "/enroll.do":
		try {
			bd.insert(new Board("제목없음",(String)request.getSession().getAttribute("email"),request.getParameter("comments"),0));
			response.sendRedirect(ctx+"/MainFrame.do");
		} catch (Exception e) {
			// TODO: handle exception
		}
		break;
	
	}
		
}

}
