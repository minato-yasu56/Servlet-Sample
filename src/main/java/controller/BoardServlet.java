package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Content;
import model.User;
import utils.CsrfTokenGenerator;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Content> contents = new ArrayList<Content>();

    public BoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		
		if (session != null && session.getAttribute("loginUser") != null) {
			User user = (User)session.getAttribute("loginUser");
			
			String csrfToken = CsrfTokenGenerator.generateToken();
			
			session.setAttribute("csrfToken", csrfToken);
			
			request.setAttribute("userId", user.getId());
			request.setAttribute("contents", contents);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/board.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
		
	}

	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("loginUser") != null) {
			
			String userId = (String) request.getAttribute("userId");
			String text = (String) request.getAttribute("text");
			
			contents.add(new Content(text, userId));
			
			response.sendRedirect("board");
		} else {
			response.sendRedirect("login");
		}
		return;
	}

}
