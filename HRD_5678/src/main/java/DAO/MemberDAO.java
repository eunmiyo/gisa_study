package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Rank;
import DTO.Voter;

public class MemberDAO {
	private static final int String = 0;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "gisa","gisa1234");
		
		return con;
	}
	
	public String listAll(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = getConnection();
			String sql = " SELECT M.M_NO, M.M_NAME, P.P_NAME, "
					+ "DECODE(M.P_SCHOOL, '1', '고졸', '2', '학사', '3', '석사', '박사') P_SCHOOL, "
					+ "substr(M.M_JUMIN,1,6)||'-'||substr(M.M_JUMIN,7) M_JUMIN, M.M_CITY , "
					+ "P.P_TEL1||'-'||P.P_TEL2||'-'||(substr(P.P_TEL3,4)||substr(P.P_TEL3,4)||substr(P.P_TEL3,4)||substr(P.P_TEL3,4)) "
					+ "from TBL_MEMBER_202005 M "
					+ "join (TBL_PARTY_202005 P) "
					+ "on(M.P_CODE = P.P_CODE) "
					+ "order by M.M_NO asc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
	
			while(rs.next()) {
				Member member = new Member();
				member.setM_no(rs.getInt(1));
				member.setM_name(rs.getString(2));
				member.setP_name(rs.getString(3));
				member.setP_school(rs.getString(4));
				member.setM_jumin(rs.getString(5));
				member.setM_city(rs.getString(6));
				member.setTel(rs.getString(7));
				
				list.add(member);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list.jsp";
	}
	
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		
		String jumin = request.getParameter("jumin");
		String name = request.getParameter("name");
		String no = request.getParameter("no");
		String time = request.getParameter("time");
		String area = request.getParameter("area");
		String check = request.getParameter("check");
		int result = 0;
		
		
		try {
			conn = getConnection();
			String sql = "insert into TBL_VOTE_202005 values(?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jumin);
			ps.setString(2, name);
			ps.setString(3, no);
			ps.setString(4, time);
			ps.setString(5, area);
			ps.setString(6, check);
			
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String voteList(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Voter> voterList = new ArrayList<Voter>();
		
		try {
			conn = getConnection();
			String sql = "SELECT V_NAME, "
					+ "SUBSTR(V_JUMIN,1,2)+1900||'년'||SUBSTR(V_JUMIN,3,2)||'월'||SUBSTR(V_JUMIN,5,2)||'일생' 생년월일, "
					+ "'만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(19||SUBSTR(V_JUMIN,1,6),'YYYYMMDD')) / 12)||'세' 나이, "
					+ "DECODE(SUBSTR(V_JUMIN, 7, 1), '1', '남자', '2', '여자', '3', '남자', '4', '여자', '5', '남자', '6', '여자') 성별, "
					+ "M_NO,SUBSTR(V_TIME, 1, 2)||':'||SUBSTR(V_TIME, 3, 2) 투표시간, "
					+ "DECODE(V_CONFIRM, 'N', '미확인', 'Y', '확인') 유권자확인 "
					+ "FROM TBL_VOTE_202005";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Voter voter = new Voter();
				voter.setName(rs.getString(1));
				voter.setJumin(rs.getString(2));
				voter.setAge(rs.getString(3));
				voter.setGender(rs.getString(4));
				voter.setNo(rs.getString(5));
				voter.setTime(rs.getString(6));
				voter.setCheck(rs.getString(7));
				
				voterList.add(voter);
			}
			request.setAttribute("voterList", voterList);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "votelist.jsp";
	}
	
	public String rankList(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Rank> ranklist = new ArrayList<Rank>();
		
		try {
			conn = getConnection();
			String sql = "SELECT V.M_NO, V.M_NAME, C.COUNTS "
					+ "FROM (SELECT M_NO, COUNT(M_NO) COUNTS FROM TBL_VOTE_202005 GROUP BY M_NO) C "
					+ "JOIN (TBL_MEMBER_202005 V) "
					+ "ON (C.M_NO = V.M_NO) "
					+ "ORDER BY C.COUNTS DESC";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Rank rank = new Rank();
				rank.setNo(rs.getInt(1));
				rank.setName(rs.getString(2));
				rank.setCounts(rs.getInt(3));
				
				ranklist.add(rank);
			}
			request.setAttribute("ranklist", ranklist);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ranklist.jsp";
	}
	
}
