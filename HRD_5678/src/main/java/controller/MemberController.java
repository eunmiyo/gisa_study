package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		MemberDAO member = new MemberDAO();
		
		switch(command) {
		case "/home" :
			site = "index.jsp";
			break;
		case "/list" :
			site = member.listAll(request, response);
			break;
		case "/vote" :
			site = "vote.jsp";
			break;
		case "/insert" :
			int result = member.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result == 1) {
				out.print("<script>");
				out.print("alert('투표하기 정보가 정상적으로 등록 되었습니다!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			} else {
				out.print("<script>");
				out.print("alert('등록실패!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			}
			break;
		case "/votelist" :
			site = member.voteList(request, response);
			break;
			
		case "/rank" :
			site = member.rankList(request, response);
			break;
					
		}
		
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);
	}
	

}
