<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Content" %>
<%@ page import="java.time.*,java.time.format.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
	<title>掲示板</title>
</head>
<body>
	<header class="header">
		<h1 class="title">Board</h1>
		<ul>
			<li>
				<%
					String userId = (String) request.getAttribute("userId");
				%>
				<h4>user : <%= userId %></h4>
			</li>
			<li>
				<form action="logout" method="post">
					<input type="submit" value="ログアウト">
				</form>	
			</li>
		</ul>
	</header>
	
	<main>
		<div class="timeline">
			<article class="post">
				<%
					List<Content> contents = (List<Content>) request.getAttribute("contents");
					
					if (contents != null) {
						for (Content content : contents) {
				%>
					<div class="post-header">
						<span class="post-id"><%= content.getId()%></span>
						<span class="post-time">
							<%
								LocalDateTime now = content.getPostTime();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
								String formattedDateTime = now.format(formatter);
							%>
							<%=formattedDateTime %>
						</span>
						<span class="post-user-id"><%= content.getPostUserId()%></span>
					</div>
					<div class="post-content">
						<p><%= content.getText()%></p>
					</div>
				<%
				
						}
					}
				%>
			</article>
		</div>
		
		<hr>
	
		<div class="form-container">
			<form action="InputPost" method="post">
				<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
				<div class="input-group">
					<textarea name="text"></textarea>
				</div>
				<input type="submit" value="送信" class="submit-btn">
			</form>
		</div>
	</main>
	
	<footer></footer>
</body>
</html>