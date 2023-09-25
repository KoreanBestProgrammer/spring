package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.FreeDto;

public class FreeDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no),'N000') as no from bike_김용석_free";
		

		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no");
				no = no.substring(1);
				int num = Integer.parseInt(no);
				num = num + 1;
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


	public int getFreeSave(FreeDto dto) {
		int result = 0;
		String query = "insert into bike_김용석_free\r\n" + 
					" (no,title,content,attach,reg_id,reg_name,reg_date,open)\r\n" + 
					" values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getReg_id()+"','"+dto.getReg_name()+"',\r\n" + 
					" to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss'),'"+dto.getOpen()+"')";
		

		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			result=ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}


	public int getTotalCount(String select, String search) {
		int count = 0;
		String query = "select count(*)as count from bike_김용석_free\r\n" + 
				"where "+select+" like '%"+search+"%'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return count;
	}


	public ArrayList<FreeDto> getFreeList(String select, String search, int start, int end) {
		ArrayList<FreeDto> arr = new ArrayList<>();
		String query="select * from\r\n" + 
				"(select rownum as rnum, tbl.* from\r\n" + 
				"(select a.no,a.title,a.reg_id,a.open,a.attach,b.name,a.hit,\r\n" + 
				"to_char(a.reg_date,'yyyy-mm-dd')as reg_date \r\n" + 
				"from bike_김용석_free a, bike_김용석_member b\r\n" + 
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
				String attach = rs.getString("attach");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				String open = rs.getString("open");
				String reg_id = rs.getString("reg_id");
				
				FreeDto dto = new FreeDto(no, title, attach, name, reg_date, hit, open, reg_id);
				arr.add(dto);
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return arr;
	}


	public void getHit(String no) {
		String query = "update bike_김용석_free\r\n" + 
					"    set hit = hit + 1\r\n" + 
					"where no = '"+no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result == 0) System.out.println("공지사항 조회수 증가 오류");
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
	}


	public FreeDto getFreeView(String no) {
		FreeDto dto = null;
		String query = "select no,title,content,reg_id,reg_name,hit,open,filehit,to_char(reg_date,'yyyy-mm-dd hh24:mi:ss')as reg_date,\r\n" + 
					"to_char(update_date,'yyyy-mm-dd hh24:mi:ss')as update_date,attach\r\n" + 
					"from bike_김용석_free\r\n" + 
					"where no = '"+no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String attach = rs.getString("attach");
				String reg_name = rs.getString("reg_name");
				String reg_id = rs.getString("reg_id");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				String update_date = rs.getString("update_date");
				int filehit  = rs.getInt("filehit");
				String open = rs.getString("open");
				
				dto = new FreeDto(no, title, content, attach, reg_id, reg_name, reg_date, update_date, hit, filehit, open);
						
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	public FreeDto getPreView(String no) {
		FreeDto dto = null;
		String query = "select a.no, b.title from\r\n" + 
				"(select max(no)as no from bike_김용석_free\r\n" + 
				"where no < '"+no+"')a , bike_김용석_free b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String preNo = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new FreeDto(preNo, title);
						
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	public FreeDto getNextView(String no) {
		FreeDto dto = null;
		String query = "select a.no, b.title from\r\n" + 
				"(select min(no)as no from bike_김용석_free\r\n" + 
				"where no > '"+no+"')a, bike_김용석_free b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String preNo = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new FreeDto(preNo, title);
						
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	public int getFreeUpdate(FreeDto dto) {
		int result = 0;
		String query = "update bike_김용석_free\r\n" + 
				"    set title = '"+dto.getTitle()+"',\r\n" + 
				"        content = '"+dto.getContent()+"',\r\n" + 
				"        attach = '"+dto.getAttach()+"',\r\n" + 
				"        update_date = to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where no = '"+dto.getNo()+"'";

		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			result=ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}


	public int getFreeDelete(String no) {
		int result = 0;
		String query="delete from bike_김용석_free\r\n" + 
					"    where no = '"+no+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			result=ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}


	public void getFileHit(String no) {
		String query="update bike_김용석_free\r\n" + 
				"    set filehit = filehit+1\r\n" + 
				"   where no = '"+no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result == 0) System.out.println("파일다운로드 조회수 증가 오류");
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
	}
	
	
	
}
