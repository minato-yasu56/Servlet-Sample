package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Account[] account = {
			new Account("tanaka", "1234"),
			new Account("Sato", "5678"),
			new Account("yamada", "9012")
	};
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		boolean isAuth = false;

		for (Account account : account) {
			if (account.id.equals(userId) && account.pass.equals(pass)) {
				request.changeSessionId();
				
				// 3. セッションスコープにユーザー情報を保存
			    HttpSession session = request.getSession();
			    User user = new User(userId);
			    session.setAttribute("loginUser", user);
				
			    response.sendRedirect("board");
			    
				isAuth = true;
				break;
			}
		}

		if (!isAuth) {
			request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", session);
		}
		return;
	}

}

class Account {
	String id;
	String pass;
	Account(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}
}