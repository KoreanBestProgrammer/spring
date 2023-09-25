package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.ProductDto;

public class ProductDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getProductNo(){
		String no = "";
		String query="select nvl(max(no),'P000') as no from bike_김용석_product";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("no");
				no = no.substring(1);
				int num = Integer.parseInt(no);
				num = num+1;
				
				DecimalFormat df = new DecimalFormat("P000");
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

	public int getSaveProduct(ProductDto dto) {
		int result = 0;
		String query="insert into bike_김용석_product\r\n" + 
				"(no,product_name,product_explain,product_size,product_price,product_photo,\r\n" + 
				"ranking,reg_date,reg_id) \r\n" + 
				"values('"+dto.getNo()+"','"+dto.getProduct_name()+"','"+dto.getProduct_explain()+"','"+dto.getProduct_size()+"','"+dto.getProduct_price()+"','"+dto.getProduct_photo()+"','"+dto.getRanking()+"',\r\n" + 
				"to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss'),'"+dto.getReg_id()+"')";
		
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

	public int getTotalCount(String select, String search, String productLevel) {
		int count = 0;
		String query = "select count(*)as count from bike_김용석_product\r\n" + 
				"where "+select+" like '%"+search+"%'\r\n" + 
				"and ranking like '%"+productLevel+"%'" ; 
				
		
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

	public ArrayList<ProductDto> getListProduct(String select, String search, int start, int end, String productLevel) {
		ArrayList<ProductDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.*from \r\n" + 
				"(select no,product_name,product_photo,to_char(reg_date,'yyyy-mm-dd')as reg_date\r\n" + 
				",hit from bike_김용석_product\r\n" + 
				"where "+select+" like '%"+search+"%'\r\n" + 
				"and ranking like '%"+productLevel+"%'\r\n" + 
				"order by reg_date desc)\r\n" + 
				"tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String product_name = rs.getString("product_name");
				String product_photo = rs.getString("product_photo");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				ProductDto dto = new ProductDto(no, product_name, product_photo, reg_date, hit);
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
	

	public ArrayList<ProductDto> getListProductIndex() {
		ArrayList<ProductDto> arr = new ArrayList<>();
		String query = "select * from \r\n" + 
				"(select rownum as rnum, tbl.*from \r\n" + 
				"(select no,product_name,product_photo,product_price \r\n" + 
				"from bike_김용석_product \r\n" + 
				"order by no desc) \r\n" + 
				"tbl) \r\n" + 
				"where rnum >= 1 and rnum <= 2";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String product_name = rs.getString("product_name");
				String product_photo = rs.getString("product_photo");
				String product_price = rs.getString("product_price");
				
				ProductDto dto = new ProductDto(no, product_name, product_price,product_photo);
				arr.add(dto);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		ProductDto dto = null;
		
		if(arr.size() <= 6) {
			for(int k = arr.size() ; k < 6 ; k++) {
				arr.add(dto);
			}
		}
		
		return arr;
	}


	public void getHit(String no) {
		String query = "update bike_김용석_product\r\n" + 
				"    SET hit = hit+1\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result == 0) System.out.println("product조회수 증가 오류");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
	}

	public ProductDto getProductView(String no) {
		ProductDto dto = null;
		String query = "select no,product_name,product_explain,product_price,product_size,product_photo,\r\n" + 
				"ranking,to_char(reg_date,'yyyy-mm-dd hh24:mi:ss') as reg_date,to_char(update_date,'yyyy-mm-dd hh24:mi:ss') as update_date,hit,reg_id from bike_김용석_product\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				String product_name = rs.getString("product_name");
				String product_explain = rs.getString("product_explain");
				String product_price = rs.getString("product_price");
				String product_size = rs.getString("product_size");
				String product_photo = rs.getString("product_photo");
				String ranking = rs.getString("ranking");
				String reg_date = rs.getString("reg_date");
				String reg_id = rs.getString("reg_id");
				int hit = rs.getInt("hit");
				String update_date = rs.getString("update_date");
				
				dto = new ProductDto(no, product_name, product_explain, product_size, product_price, product_photo, ranking, reg_date, reg_id, hit, update_date);
			}
		}catch(Exception e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
				
		return dto;
	}

	public ProductDto getPreView(String no) {
		ProductDto preDto = null;
		String query = "select a.no,b.product_name from\r\n" + 
				"(select max(no) as no from bike_김용석_product\r\n" + 
				"where no < '"+no+"')a, bike_김용석_product b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				String preNo = rs.getString("no");
				String product_name = rs.getString("product_name");
				
				preDto = new ProductDto(preNo, product_name);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return preDto;
	}

	public ProductDto getNextView(String no) {
		ProductDto nextDto = null;
		String query = "select a.no, b.product_name from\r\n" + 
				"(select min(no) as no from bike_김용석_product\r\n" + 
				"where no > '"+no+"')a, bike_김용석_product b\r\n" + 
				"where a.no = b.no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				String nextNo = rs.getString("no");
				String product_name = rs.getString("product_name");
				
				nextDto = new ProductDto(nextNo, product_name);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return nextDto;
	}

	public int getUpdateProduct(ProductDto dto) {
		int result = 0;
		String query = "update bike_김용석_product\r\n" + 
				"    set product_name='"+dto.getProduct_name()+"',\r\n" + 
				"        product_explain='"+dto.getProduct_explain()+"',\r\n" + 
				"        product_price='"+dto.getProduct_price()+"',\r\n" + 
				"        product_size='"+dto.getProduct_size()+"',\r\n" + 
				"        ranking = '"+dto.getRanking()+"',\r\n" + 
				"        product_photo='"+dto.getProduct_photo()+"',\r\n" + 
				"        update_date=to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"    where no = '"+dto.getNo()+"'";
		
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

	public int getDeleteProduct(String no) {
		int result = 0;
		String query="delete from bike_김용석_product\r\n" + 
				"    where no = '"+no+"'";
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
	
}
