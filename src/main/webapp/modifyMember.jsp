<%@page import="com.ddu.dto.MemberDto"%>
<%@page import="com.ddu.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<%
		String sid = (String) session.getAttribute("sessionId");
		if(sid == null){// 세선에 sessionId 값이 없음 -> 로그인 하지 않고 본 페이지 방문
			out.print("<script>alert('로그인 하지 않은 유저는 수정 페이지 접근 불가 !'); window.location.href='loginMember.jsp';</script>");
		}else { // 로그인 성공 상태
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.getMemberInfo(sid);
			
			request.setAttribute("memberDto", memberDto);
			// el 표기법 사용시 
		}
	%>
	<h2>회원 정보 수정</h2>
	<hr>
	<form action="modifyMemberOk.jsp" method="post">
		아이디 : <input type="text" name="memberid" value="${memberDto.memberid }" readonly="readonly"><br><br>
		비밀번호 : <input type="password" name="memberpw" value="${memberDto.memberpw }"><br><br>
		이 름 : <input type="text" name="membername" value="${memberDto.membername }"><br><br>
		나 이 : <input type="text" name="memberage" value="${memberDto.memberage }"><br><br>
		이메일 : <input type="text" name="memberemail" value="${memberDto.memberemail }"><br><br>
		등록일 : <input type="text" name="memberdate" value="${memberDto.memberdate }" readonly="readonly"><br><br>
		<input type="submit" value="수정 완료">
	</form>
</body>
</html>