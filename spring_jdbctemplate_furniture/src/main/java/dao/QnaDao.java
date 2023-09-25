package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.QnaDto;

public class QnaDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	
	public String getQnaNo() {
		String no = "";
		String query = "select nvl(max(no),'N000')as no from bike_김용석_qna";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no"); // "N001"
				no = no.substring(1); // "001"
			    int intNo = Integer.parseInt(no); //1
			    intNo = intNo + 1; //2
			    DecimalFormat df = new DecimalFormat("N000");
			    no = df.format(intNo); //"N002"
			    
			}
		}catch(Exception e) {
			System.out.println("쿼리문 오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}		
		
		return no;
	}



	public int getSaveQna(QnaDto dto) {
		int result = 0;
		String query="insert into bike_김용석_qna\r\n" + 
				"(no,title,content,attach,state,reg_id,reg_date)\r\n" + 
				"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getState()+"','"+dto.getReg_id()+"',\r\n" + 
				"to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss'))";
				
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}



	public int getTotalCount(String select, String search) {
		int count = 0;
		String query="select count(*) as count from bike_김용석_qna\r\n" + 
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



	public ArrayList<QnaDto> getQnaList(int start, int end, String select, String search) {
		ArrayList<QnaDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from\r\n" + 
				"(select no,title,reg_id,answer,to_char(reg_date,'yyyy-mm-dd')as reg_date,attach\r\n" + 
				"from bike_김용석_qna\r\n" + 
				"where "+select+" like '%"+search+"%'\r\n" + 
				"order by no desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				String reg_id = rs.getString("reg_id");
				String reg_date = rs.getString("reg_date");
				String answer = rs.getString("answer");
				
				QnaDto dto = new QnaDto(no, title, attach, reg_date, reg_id, answer);
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



	public QnaDto getQnaView(String no) {
		QnaDto dto = null;
		String query = "select no,title,content,to_char(reg_date,'yyyy-mm-dd hh24:mi:ss')as reg_date,\r\n" + 
				"reg_id,to_char(update_date,'yyyy-mm-dd hh24:mi:ss')as update_date,\r\n" + 
				"attach,answer,answer_id,to_char(admin_update_date,'yyyy-mm-dd hh24:mi:ss')as admin_update_date,\r\n" + 
				"to_char(answer_date,'yyyy-mm-dd hh24:mi:ss')as answer_date\r\n" + 
				"from bike_김용석_qna\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String answer_id = rs.getString("answer_id");
				String admin_update_date = rs.getString("admin_update_date");
				String update_date = rs.getString("update_date");
				String attach = rs.getString("attach");
				String reg_id = rs.getString("reg_id");
				String reg_date = rs.getString("reg_date");
				String answer = rs.getString("answer");
				String answer_date = rs.getString("answer_date");
				
				dto = new QnaDto(no, title, content, attach, reg_date, update_date, reg_id, answer, answer_id, admin_update_date, answer_date);
				
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}



	public int getQnaUpdate(QnaDto dto) {
		int result = 0;
		String query="update bike_김용석_qna\r\n" + 
				"    set title = '"+dto.getTitle()+"',\r\n" + 
				"        content = '"+dto.getContent()+"',\r\n" + 
				"        attach = '"+dto.getAttach()+"',\r\n" + 
				"        update_date = to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where no = '"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}



	public int getQnaDelete(String no) {
		int result =0;
		String query="delete from bike_김용석_qna\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}



	public int getAnswerSave(QnaDto dto) {
		int result = 0;
		String query="update bike_김용석_qna\r\n" + 
				"    set answer = '"+dto.getAnswer()+"',\r\n" + 
				"        answer_id = '"+dto.getAnswer_id()+"',\r\n" + 
				"        answer_date = to_date('"+dto.getAnswer_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"     where no = '"+dto.getNo()+"'";
			
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}



	public int getAnswerUpdate(QnaDto dto) {
		int result=0;
		String query="update bike_김용석_qna\r\n" + 
				"    set answer = '"+dto.getAnswer()+"',\r\n" + 
				"        admin_update_date = to_date('"+dto.getAdmin_update_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"    where no = '"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}



	public int getAnswerDelete(QnaDto dto) {
		int result = 0;
		String query="update bike_김용석_qna\r\n" + 
				"     set answer = '"+dto.getAnswer()+"',\r\n" + 
				"        admin_update_date = to_date('"+dto.getAdmin_update_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"     where no = '"+dto.getNo()+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);		
		}
		
		return result;
	}
	
	
	
}
