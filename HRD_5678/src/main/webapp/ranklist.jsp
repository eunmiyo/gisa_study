<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import="java.util.*" %>
<%@page import="DTO.Rank" %>

<%
request.setCharacterEncoding("UTF-8");
ArrayList<Rank> ranklist = new ArrayList<Rank>();
ranklist = (ArrayList<Rank>)request.getAttribute("ranklist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="top.jsp" %>
		<section>	
		<div class="title">후보자등수</div>
		<div class="wrapper">
			<table>
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
				<% for(Rank r : ranklist) { %>
				<tr>
					<td><%=r.getNo() %></td>
					<td><%=r.getName() %></td>
					<td><%=r.getCounts() %></td>
				</tr>
				<% } %>
			</table>
		</div>
		</section>
	<%@ include file="footer.jsp" %>
</body>
</html>