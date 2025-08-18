package com.ddu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ddu.dto.MemberDto;

public class MemberDao {

	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jspdb";
	private String username = "root";
	private String password = "12345";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_FAIL = 0;
	int sqlResult = 0;
	
	public int insertMember(MemberDto memberDto) { // 회원가입 메서드
		String sql = "INSERT INTO membertbl(memberid,memberpw,membername,memberage,memberemail) VALUES(?,?,?,?,?)";
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, memberDto.getMemberid());
			pstmt.setString(2, memberDto.getMemberpw());
			pstmt.setString(3, memberDto.getMembername());
			pstmt.setInt(4, memberDto.getMemberage());
			pstmt.setString(5, memberDto.getMemberemail());
			
			sqlResult = pstmt.executeUpdate(); // 회원가입 성공하면 sqlResult 값이 1로 변환
			
		} catch(Exception e) {	
			System.out.println("회원가입 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
		if (sqlResult == 1) {
			return MEMBER_JOIN_SUCCESS; // 1
		} else {
			return MEMBER_JOIN_FAIL; // 0
		}
	}
	  public int loginCheck(String id , String pw) {
		  String sql = "SELECT * FROM membertbl WHERE memberid=? AND memberpw=?";
		  int sqlResult = 0;
		  try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);	
				pstmt = conn.prepareStatement(sql); 
				
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				rs = pstmt.executeQuery(); // 회원가입 성공하면 sqlResult 값이 1로 변환
				
				if (rs.next()) { // 로그인 성공
					sqlResult =MEMBER_LOGIN_SUCCESS;
				} else { // 로그인 실패
					sqlResult =MEMBER_LOGIN_FAIL;
				}
				
			} catch(Exception e) {	
				System.out.println("로그인 체크 실패");
				e.printStackTrace();
			} finally { 
				try {
					if (rs != null) {
					rs.close();
					}
					if(pstmt != null){
					pstmt.close();
					}
					if(conn != null){ 	
					conn.close();
					} 
				} catch(Exception e) {
					e.printStackTrace();
				}	
				
			}
		  return sqlResult; // 1로 반환되면 로그인 성공 0으로 반환되면 로그인 실패
	  }
	 
	
	
}
