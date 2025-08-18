<%@page import="com.ddu.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 성공</title>
</head>
<body>
	<%
		String mid = request.getParameter("deleteid");
	
		MemberDao memberDao = new MemberDao();
		int loginResult = memberDao.deleteId(mid);
		
		if(loginResult == MemberDao.ID_DELETE_SUCCESS){
			out.print("<script>alert('회원 탈퇴 성공!')</script>");
			//response.sendRedirect("loginMember.jsp");
		} else{
			out.print("<script>alert('존재하지 않는 아이디 입니다.');history.go(-1);</script>");
		}
	%>
</body>
</html>