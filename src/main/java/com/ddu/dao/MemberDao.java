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
	public static final int ID_USE_SUCCESS = 1;
	public static final int ID_USE_FAIL = 0;
	public static final int ID_DELETE_SUCCESS = 1;
	public static final int ID_DELETE_FAIL = 0;
	public static final int MEMBER_UPDATE_SUCCESS = 1;
	public static final int MEMBER_UPDATE_FAIL = 0;
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
				rs = pstmt.executeQuery(); 
				
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
	  
	  public int confirmId (String id) {
		  String sql ="SELECT memberid FROM membertbl WHERE memberid=?";
		  int sqlResult = 0;
		  try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);	
				pstmt = conn.prepareStatement(sql); 
				
				pstmt.setString(1, id);

				rs = pstmt.executeQuery(); 
				
				if (rs.next()) { // 로그인 실패
					sqlResult =ID_USE_FAIL;
				} else { // 로그인 성공
					sqlResult =ID_USE_SUCCESS;
				}
				
			} catch(Exception e) {	
				System.out.println("아이디 체크 실패");
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
		  return sqlResult;
		  
		  
	
	  }
	  public int deleteId (String id) {
		  String sql ="DELETE FROM membertbl WHERE memberid=?";
		  int sqlResult = 0;
		  try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);	
				pstmt = conn.prepareStatement(sql); 
				
				pstmt.setString(1, id);

				sqlResult = pstmt.executeUpdate(); // 회원가입 성공하면 sqlResult 값이 1로 변환
				
				if (sqlResult == 1) { // 회원 탈퇴 성공
					sqlResult = ID_DELETE_SUCCESS;
				} else { // 회원 탈퇴 실패
					sqlResult =ID_DELETE_FAIL;
				}
				
			} catch(Exception e) {	
				System.out.println("아이디 조회 실패");
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
	  }return sqlResult;
	  }
	  public MemberDto getMemberInfo(String memberId) {
		  String sql = "SELECT * FROM membertbl WHERE memberid=?";
		  MemberDto memberDto = new MemberDto();
		  try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);	
				pstmt = conn.prepareStatement(sql); 
				
				pstmt.setString(1 , memberId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					memberDto.setMemberid(rs.getString("memberid"));
					memberDto.setMemberpw(rs.getString("memberpw"));
					memberDto.setMembername(rs.getString("membername"));
					memberDto.setMemberage(rs.getInt("memberage"));
					memberDto.setMemberemail(rs.getString("memberemail"));
					memberDto.setMemberdate(rs.getString("memberdate"));
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
				
			}return memberDto;
	  }
		public int updateMember(String memberid, String memberpw, String membername, int memberage, String memberemail) { // 회원가입 메서드
			String sql = "UPDATE membertbl SET memberpw=?,membername=?,memberage=?,memberemail=? WHERE memberid=?";
			int sqlResult =0;
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);	
				pstmt = conn.prepareStatement(sql); 
				
				pstmt.setString(1, memberpw);
				pstmt.setString(2, membername);
				pstmt.setInt(3, memberage);
				pstmt.setString(4, memberemail);
				pstmt.setString(5, memberid);
				
				sqlResult = pstmt.executeUpdate(); // 회원가입 성공하면 sqlResult 값이 1로 변환
				
			} catch(Exception e) {	
				System.out.println("회원 수정 실패");
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
				return MEMBER_UPDATE_SUCCESS; // 1
			} else {
				return MEMBER_UPDATE_FAIL; // 0
			}  
	  }
}
