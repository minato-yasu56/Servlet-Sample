package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;

import model.User;

@WebServlet("/InputPost")
public class InputPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InputPostServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("loginUser") != null) {


			String savedToken = (String) session.getAttribute("csrfToken"); // サーバー側に保存したTokenを取得
			String requestToken = request.getParameter("csrfToken"); // リクエストに含まれる（ユーザから送られた）Tokenを取得

			// referrerヘッダのURLを取得
			String referrer = request.getHeader("Referer");

			if (referrer != null && referrer.equals("http://localhost:8080/Sample-Web-App/board")) {
				if (savedToken != null && savedToken.equals(requestToken)) {
					User user = (User)session.getAttribute("loginUser");
					
					String text = request.getParameter("text");
					
					String escapedText = StringEscapeUtils.escapeHtml4(text); // 入力値をエスケープ処理
					
					request.setAttribute("text", escapedText);
					request.setAttribute("userId", user.getId());
					
					RequestDispatcher rd = request.getRequestDispatcher("board");
					rd.forward(request, response);				
				}
			}
		} else {
			response.sendRedirect("login");
		}
	}
}
