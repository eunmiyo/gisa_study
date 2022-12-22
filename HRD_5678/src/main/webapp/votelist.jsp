<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import="java.util.*" %>
<%@page import="DTO.Voter" %>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Voter> voterList = new ArrayList<Voter>();
voterList = (ArrayList<Voter>)request.getAttribute("voterList");
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
		<div class="title">투표검수조회</div>
		<div class="wrapper ">
			<table>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
				<% for(Voter v : voterList ) { %>
				<tr>
					<td><%=v.getName() %></td>
					<td><%=v.getJumin() %></td>
					<td><%=v.getAge() %></td>
					<td><%=v.getGender() %></td>
					<td><%=v.getNo() %></td>
					<td><%=v.getTime() %></td>
					<td><%=v.getCheck() %></td>
				</tr>
				<% } %>
			</table>
		</div>
	<%@ include file="footer.jsp" %>
</body>
</html>