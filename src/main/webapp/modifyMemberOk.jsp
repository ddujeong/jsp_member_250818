<%@page import="com.ddu.dto.MemberDto"%>
<%@page import="com.ddu.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 완료</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		
		String memberid = request.getParameter("memberid");
		String memberpw = request.getParameter("memberpw");
		int memberage = Integer.parseInt(request.getParameter("memberage")) ;
		String membername = request.getParameter("membername");
		String memberemail = request.getParameter("memberemail");
		
		MemberDao memberDao = new MemberDao();
		int updateResult = memberDao.updateMember(memberid, memberpw, membername, memberage, memberemail);
		
		if (updateResult == MemberDao.MEMBER_UPDATE_FAIL){
			out.print("<script>alert('회원 수정 실패!');history.go(-1);</script>");
		} else{
			out.print("<script>alert('회원 수정 완료!');</script>");
			MemberDto memberDto = memberDao.getMemberInfo(memberid);
			request.setAttribute("memberDto", memberDto);
		}
	%>
	<h2>수정된 회원 정보 확인</h2>
	<hr>
	아이디 : ${memberDto.memberid}<br><br>
	비밀번호 : ${memberDto.memberpw}<br><br>
	이름 : ${memberDto.membername}<br><br>
	나이 : ${memberDto.memberage}<br><br>
	이메일 : ${memberDto.memberemail}<br><br>

	<a href="logout.jsp">로그아웃</a>
</body>
</html>