<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import="java.util.*" %>
<%@page import="DTO.Rank" %>

<%
request.setCharacterEncoding("UTF-8");
ArrayList<>  = new ArrayList<>();
voterList = (ArrayList<>)request.getAttribute("voterList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="top.jsp" %>
		<div class="title">후보자등수</div>
		<div class="wrapper">
			<table>
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	<%@ include file="footer.jsp" %>
</body>
</html>