<%@page import="com.ddu.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<%
		String mid = request.getParameter("loginid");
		String mpw = request.getParameter("loginpw");
	
		MemberDao memberDao = new MemberDao();
		int loginResult = memberDao.loginCheck(mid, mpw);
		
		if(loginResult == MemberDao.MEMBER_LOGIN_SUCCESS){
			session.setAttribute("sessionId", mid); // 세션에 로그인 성공한 아이디 set
			out.print("<script>alert('로그인 성공!')</script>");
		} else{
			out.print("<script>alert('로그인 실패! 아이디 또는 비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
		}
	%>
	<h3>
		<a href="modifyMember.jsp">회원 정보 수정으로 바로가기</a>
	</h3>
</body>
</html>