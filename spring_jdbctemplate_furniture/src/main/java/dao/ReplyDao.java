package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnection;

import dto.ReplyDto;

public class ReplyDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public int getReplySave(ReplyDto dto) {
		int result = 0;
		String query="insert into bike_김용석_coments\r\n" + 
				"(c_no,no,noname,reply,reply_date,reply_name)\r\n" + 
				"values('"+dto.getC_no()+"','"+dto.getNo()+"','"+dto.getNoname()+"','"+dto.getReply()+"',to_date('"+dto.getReply_date()+"','yyyy-mm-dd hh24:mi:ss'),'"+dto.getReply_name()+"')";
		
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
	public ArrayList<ReplyDto> getReplyList(String order_coment, int start, int end, String no) {
		ArrayList<ReplyDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from\r\n" + 
				"(select reply,noname,to_char(reply_date,'yyyy-mm-dd hh24:mi:ss')as reply_date,reply_name \r\n" + 
				"from bike_김용석_coments\r\n" + 
				"where no = '"+no+"'\r\n" + 
				"order by reply_date "+order_coment+")tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String reply = rs.getString("reply");
				String noname = rs.getString("noname");
				String reply_date = rs.getString("reply_date");
				reply_date = CommonUtil.convertDate(reply_date);
				String reply_name = rs.getString("reply_name");
				
				
				ReplyDto dto = new ReplyDto(noname, reply, reply_date, reply_name);
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
	public int getTotalCount(String no) {
		int count = 0;
		String query = "select count(*)as count from bike_김용석_coments\r\n" + 
				"where no = '"+no+"'";
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
	public String getC_no() {
		String c_no = "";
		String query = "select nvl(max(c_no),'C000')as c_no from bike_김용석_coments";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				c_no = rs.getString("c_no");
				c_no = c_no.substring(1);
				int num = Integer.parseInt(c_no);
				num = num+1;
				DecimalFormat df = new DecimalFormat("C000");
				c_no = df.format(num);
				
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return c_no;
	}
	
	
	
	
	
	
}
