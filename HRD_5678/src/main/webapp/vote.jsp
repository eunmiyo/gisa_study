<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="submit.js"></script>
</head>
<body>
	<%@ include file="top.jsp" %>
		<div class="title">투표하기</div>
		<form name="frm" action="insert">
			<input type="hidden" id="GUBUN" value="insert" >
		<div class="wrapper">
			<table>
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="jumin" size=30>예: 8906153154726</td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<th>투표번호</th>
					<td><select name="no" id="no_sel">
						<option></option>
						<option value="1">[1] 김후보</option>
						<option value="2">[2] 이후보</option>
						<option value="3">[3] 박후보</option>
						<option value="4">[4] 조후보</option>
						<option value="5">[5] 최후보</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>투표시간</th>
					<td><input type="text" name="time" ></td>
				</tr>
				<tr>
					<th>투표장소</th>
					<td><input type="text" name="area" ></td>
				</tr>
				<tr>
					<th>유권자확인</th>
					<td class="chk">
					<input type="radio" name="check" value="Y">확인
					<input type="radio" name="check" value="N">미확인
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<button class="btn" type="submit" onclick="fn_submit(); return false;">투표하기</button>
					<button class="btn" type="reset" onclick="fn_al();">다시하기</button>
					</td>
				</tr>				
			</table>
		</div>
		</form>
	
	<%@ include file="footer.jsp" %>
</body>
</html>