package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.EtcDto;


public class EtcDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no),'N000') as no from bike_김용석_etc";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no");
				no = no.substring(1);
				int num = Integer.parseInt(no);
				num = num+1;
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(num);
				
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return no;
	}
	



	public int getEtcSave(EtcDto dto) {
		int result = 0;
		String query = "insert into bike_김용석_etc\r\n" + 
				"(no,title,content,reg_date,reg_id,reg_name)\r\n" + 
				"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"',to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss'),'"+dto.getReg_id()+"','"+dto.getReg_name()+"')";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			result=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}



	public int getTotalCount(String select, String search) {
		int count = 0;
		String query = "select count(*) as count from bike_김용석_etc\r\n" + 
					   "where "+select+" like '%"+search+"%'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}



	public ArrayList<EtcDto> getEtcList(String select, String search, int end, int start) {
		ArrayList<EtcDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from\r\n" + 
				"(select a.no,a.title,b.name,to_char(a.reg_date,'yyyy-mm-dd') as reg_date \r\n" + 
				"from bike_김용석_etc a, bike_김용석_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and "+select+" like '%"+search+"%'\r\n" + 
				"order by a.reg_date desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				
				EtcDto dto = new EtcDto(no, title, name, reg_date);
				arr.add(dto);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}



	public EtcDto getEtcView(String no) {
		EtcDto dto = null;
		String query = "select a.title,a.content,a.reg_id,b.name,to_char(a.reg_date,'yyyy-mm-dd hh24:mi:ss')as reg_date\r\n" + 
				"from bike_김용석_etc a, bike_김용석_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and no = '"+no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String content = rs.getString("content");
				String title = rs.getString("title");
				String name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				String reg_id = rs.getString("reg_id");
				
				dto = new EtcDto(no,title,content,reg_id,name,reg_date);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}



	public EtcDto getEtcPreView(String no) {
		EtcDto preDto = null;
		String query = "select a.no, b.title from\r\n" + 
				"(select max(no)as no from bike_김용석_etc \r\n" + 
				"where no < '"+no+"') a, bike_김용석_etc b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String preNo = rs.getString("no");
				String title = rs.getString("title");
				
				
				preDto = new EtcDto(preNo,title);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return preDto;
	}



	public EtcDto getEtcNextView(String no) {
		EtcDto nextDto = null;
		String query = "select a.no, b.title from\r\n" + 
				"(select min(no)as no from bike_김용석_etc \r\n" + 
				"where no > '"+no+"') a, bike_김용석_etc b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String nextNo = rs.getString("no");
				String title = rs.getString("title");
				
				
				nextDto = new EtcDto(nextNo,title);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return nextDto;
	}



	



	
}
