package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.NewsDto;

public class NewsDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//no
	public String getMaxNo() {
		String no = "";
		String query = "select nvl(max(no),'N000') as no \r\n" + 
					   "from home_김용석_news";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no");
				no = no.substring(1);
				int n = Integer.parseInt(no); 
				n = n+1;
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(n);
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return no;
	}
	//저장
	public int getNewsSave(NewsDto dto) {
		int result = 0;
		String query = "insert into home_김용석_news\r\n" + 
					   "(no,title,content,attach,reg_id,reg_date)\r\n" + 
					   "values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getReg_id()+"', \r\n" + 
					   "to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
					   ")";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			result=ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	//리스트 조회
	public ArrayList<NewsDto> getNewsList(String gubun,String search,int start,int end) {
		ArrayList<NewsDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"    (select rownum as rnum, tbl.* from\r\n" + 
				"        (select n.no,n.title,n.content,n.attach,m.name,to_char(n.reg_date,'yyyy-mm-dd')as reg_date,n.hit \r\n" + 
				"        from home_김용석_news n, home_김용석_member m\r\n" + 
				"        where n.reg_id = m.id\r\n" + 
				"        and "+gubun+" like '%"+search+"%'\r\n" + 
				"        order by n.no desc)tbl)\r\n" + 
				"        where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");	
				String attach = rs.getString("attach");
				String name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				NewsDto dto = new NewsDto(no,title,attach,reg_date,name,hit);
				arr.add(dto);
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	//view,수정폼
	public NewsDto getNewsView(String no) {
		NewsDto dto = null;
		String query = "select n.no,n.title,n.content,n.attach,m.name,to_char(n.reg_date,'yyyy-mm-dd hh24:mi:ss')as reg_date,\r\n" + 
				       "to_char(n.update_date,'yyyy-mm-dd hh24:mi:ss') as update_date ,n.hit \r\n" + 
				       "from home_김용석_news n, home_김용석_member m\r\n" + 
					   "where n.reg_id = m.id\r\n" + 
					   "and n.no = '"+no+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {				
				String title = rs.getString("title");
				String content = rs.getString("content");
				String attach = rs.getString("attach");
				String name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				String update_date = rs.getString("update_date");
				int hit = rs.getInt("hit");
				
				dto = new NewsDto(no,title,content,attach,reg_date,update_date,name,hit);
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	//hit
		public void getHit(String no) {
			String query = "update home_김용석_news\r\n" + 
					       "set hit = hit+1\r\n" + 
					       "where no = '"+no+"'";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				int result = ps.executeUpdate();
				if(result == 0) System.out.println("공지사항 조회수 증가 오류");
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);		
			}
				
		}
		//이전글
		public NewsDto getPreView(String no) {
			NewsDto dto = null;
			String query = "select a.no,b.title\r\n" + 
						   "from \r\n" + 
						   "(select max(no) as no from home_김용석_news \r\n" + 
						   "where no < '"+no+"') a, home_김용석_news b\r\n" + 
						   "where a.no = b.no";
			
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(query);
				rs=ps.executeQuery();
				if(rs.next()) {
					String preNo = rs.getString("no");
					String title = rs.getString("title");
					
					dto = new NewsDto(preNo,title);
				}
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);			
			}
			
			return dto;
		}
		//다음글
		public NewsDto getNextView(String no) {
			NewsDto dto = null;
			String query = "select a.no,b.title\r\n" + 
					       "from \r\n" + 
					       "(select min(no) as no from home_김용석_news \r\n" + 
					       "where no > '"+no+"') a, home_김용석_news b\r\n" + 
					       "where a.no = b.no";	
			
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(query);
				rs=ps.executeQuery();
				if(rs.next()) {
					String nextNo = rs.getString("no");
					String title = rs.getString("title");
					
					dto = new NewsDto(nextNo,title);
				}
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);			
			}
			
			return dto;
		}
		//수정
		public int getUpdateNews(NewsDto dto) {
			int result = 0;
			String query="update home_김용석_news\r\n" + 
					"    set title = '"+dto.getTitle()+"',\r\n" + 
					"        content = '"+dto.getContent()+"',\r\n" + 
					"        update_date = to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss'),\r\n" + 
					"        attach = '"+dto.getAttach()+"'\r\n" + 
					"where no = '"+dto.getNo()+"'";
			
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(query);
				result=ps.executeUpdate();
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
		//삭제
		public int getNewsDelete(String no) {
			int result = 0;
			String query = "delete from home_김용석_news\r\n" + 
					       "where no = '"+no+"'";
			
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(query);
				result=ps.executeUpdate();
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs); 
			}
			return result;
		}
		public int getTotalCount(String gubun,String select) {
			int count = 0;
			String query = "select count(*)as count from home_김용석_news n\r\n" + 
					"where "+gubun+" like '%"+select+"%'";
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(query);
				rs=ps.executeQuery();
				if(rs.next()) {
					count = rs.getInt("count");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return count;
		}
}

