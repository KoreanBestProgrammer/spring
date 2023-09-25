package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.NoticeDto;

public class NoticeDao {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		//넘버
	public String getMaxNo() {
		String no = "";
		String query= "select nvl(max(no),'N000') as no from home_김용석_notice ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no"); //	"N001"
				no = no.substring(1); // "001"
				int n = Integer.parseInt(no); // 1
				n += 1; // 2 -> "002"
				
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(n); // "N002"
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
	public int noticeSave(NoticeDto dto) {
		int result = 0;
		/*String query = "insert into home_김용석_notice \r\n" + 
					   "(no,title,content,attach,reg_id,reg_date)\r\n" + 
					   "values('"+dto.getNo()+"','"+dto.getTitle()+"','"+
					   dto.getContent()+"','','"+dto.getReg_id()+"','"+dto.getReg_date()+"')";*/
		
		String query = "insert into home_김용석_notice \r\n" + 
				"(no,title,content,attach,reg_id,reg_date)\r\n" + 
				"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getReg_id()+"',\r\n" + 
				"        to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh:mi:ss')\r\n" + 
				")";
		System.out.println(query);
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
	//list,검색
	public ArrayList<NoticeDto> getNoticeList(String select,String search){
		ArrayList<NoticeDto> arr = new ArrayList<>();
		String query = "select n.no,n.title,n.attach,m.name,to_char(n.reg_date,'yyyy-mm-dd')as reg_date,n.hit\r\n" + 
					   "from home_김용석_notice n, home_김용석_member m\r\n" + 
					   "where n.reg_id = m.id\r\n" +
					   "and "+select+" like '%"+search+"%'  "+
					   "order by n.no desc";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				String name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				NoticeDto dto = new NoticeDto(no,title,attach,name,reg_date,hit);
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
	//상세조회(view)
	public NoticeDto getNoticeView(String no) {
			NoticeDto dto = null;
			String query = "select n.no,n.title,n.content,n.attach,m.name,to_char(n.reg_date,'yyyy-mm-dd hh:mi:ss')as reg_date,n.hit\r\n" + 
					       "from home_김용석_notice n, home_김용석_member m\r\n" + 
					       "where n.reg_id = m.id\r\n" + 
					       "and n.no = '"+no+"'";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				if(rs.next()) {
					String title = rs.getString("title");
					String content = rs.getString("content");
					String attach = rs.getString("attach");
					String reg_name = rs.getString("name");
					String reg_date = rs.getString("reg_date");
					int hit = rs.getInt("hit");
					
					dto = new NoticeDto(no,title,content,attach,reg_name,reg_date,hit);
				}					
			}catch(Exception e) {
				System.out.println("쿼리오류"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);		
			}
						
			return dto;
	}
	//조회수 증가
	public void setHitCount(String no) {
		String query = "update home_김용석_notice\r\n" + 
				       "set hit = hit + 1\r\n" + 
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
	public NoticeDto getPreNotice(String no) {
		NoticeDto dto = null;
		String query = "select a.no,b.title \r\n" + 
				       "from \r\n" + 
				       "(select max(no) as no from home_김용석_notice\r\n" + 
				       "where no < '"+no+"') a, home_김용석_notice b\r\n" + 
				       "where a.no = b.no";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String minNo = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new NoticeDto(minNo,title);
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
	public NoticeDto getNextNotice(String no) {
		NoticeDto dto = null;
		String query = "select a.no,b.title \r\n" + 
				       "from \r\n" + 
				       "(select min(no) as no from home_김용석_notice\r\n" + 
				       "where no > '"+no+"') a, home_김용석_notice b\r\n" + 
				       "where a.no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String maxNo = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new NoticeDto(maxNo,title);
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dto;
	}
	public int noticeUpdate(NoticeDto dto) {
		int result = 0;
		String query = "update home_김용석_notice\r\n" + 
					   "    set title='"+dto.getTitle()+"',\r\n" + 
					   "        content='"+dto.getContent()+"', \r\n" + 
					   "        attach='"+dto.getAttach()+"'"+
					   "where no='"+dto.getNo()+"'";
		
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
	public int noticeDelete(String no) {
		int result = 0;
		String query = "delete from home_김용석_notice\r\n" + 
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
	//페이지 개수
	public int getTotalCount(String select, String search) {
		int count = 0;
		String query="select count(*) as count from home_김용석_notice n\r\n" + 
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
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
	
	//list,검색
		public ArrayList<NoticeDto> getNoticeListPage(String select,String search,int start,int end){
			ArrayList<NoticeDto> arr = new ArrayList<>();
			String query = "select * from\r\n" + 
					       "(select rownum as rnum, tbl. * from\r\n" + 
					       "    (select n.no,n.title,n.attach,m.name,to_char(n.reg_date,'yyyy-mm-dd')as reg_date,n.hit\r\n" + 
					       "    from home_김용석_notice n, home_김용석_member m\r\n" + 
					       "    where n.reg_id = m.id\r\n" + 
					       "    and "+select+" like '%"+search+"%' \r\n" + 
					       "    order by n.no desc) tbl)\r\n" + 
					       "where rnum >="+start+" and rnum <="+end+"";
		
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()) {
					String no = rs.getString("no");
					String title = rs.getString("title");
					String attach = rs.getString("attach");
					String name = rs.getString("name");
					String reg_date = rs.getString("reg_date");
					int hit = rs.getInt("hit");
					
					NoticeDto dto = new NoticeDto(no,title,attach,name,reg_date,hit);
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
	
}
