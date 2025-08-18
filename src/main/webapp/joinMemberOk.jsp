<%@page import="com.ddu.dto.MemberDto"%>
<%@page import="com.ddu.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="memberDto" class="com.ddu.dto.MemberDto"></jsp:useBean>
 <jsp:setProperty property="*" name="memberDto"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 확인</title>
</head>
<body>
	<%
		/* String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		int mage = Integer.parseInt(request.getParameter("memberage"));
		String memail = request.getParameter("memberemail");
		 */
		/* MemberDto memberDto = new MemberDto();
		memberDto.setMemberid(mid);
		memberDto.setMemberpw(mpw);
		memberDto.setMembername(mname);
		memberDto.setMemberage(mage);
		memberDto.setMemberemail(memail); */
		
		MemberDao memberDao = new MemberDao();
		
		String mid = request.getParameter("loginid");
		//int joinResult =  memberDao.insertMember(memberDto);
		// 1(성공) or 0(실패) 반환
		//int joinResult = 0;
		//memberDto.setMemberid(mid);
		int idResult = memberDao.confirmId(mid);
		// 0 (성공) or 1(실패) 반환
		
		if(idResult == MemberDao.ID_USE_FAIL){
			out.print("<script>alert('이미 존재하는 아이디 입니다');history.go(-1);</script>");
		}else {
			int  joinResult =  memberDao.insertMember(memberDto);
			if(joinResult == MemberDao.MEMBER_JOIN_SUCCESS){
				out.print("<script>alert('회원가입 성공!')</script>");
				// response.sendRedirect("loginMember.jsp");
			} else {
				out.print("<script>alert('회원가입 실패!');history.go(-1);</script>");
			}
		}
		
		
	%>
</body>
</html>