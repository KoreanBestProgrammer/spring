package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import common.CommonUtil;
import common.DBConnection;
import common.RandomString;
import dto.ProductsaleDto;

public class ProductsaleDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	public String getRandomNo(){
		String no = UUID.randomUUID().toString();
	/*	
		Random r = new Random();
		RandomString rs = new RandomString(11,r);
		String no = rs.nextString();
	*/	
		return no;
	}

	
	public String getDeliveryNo() {
		String no = "";
		String today = CommonUtil.getToday();
		today = today.replaceAll("-", "");
		String query="select nvl(max(d_no),'S"+today+"000') as d_no from bike_김용석_productsale \r\n" ; 
					
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				no = rs.getString("d_no"); //S20230630007
				
				String last_date = no.substring(1, 9);
				if(!today.equals(last_date)) {
					no = "S"+today+"001"; 
				}else {
					int number = Integer.parseInt(no.substring(9))+1; //2
					DecimalFormat df = new DecimalFormat("000"); 
					String order = df.format(number); //002
					no = no.substring(0,9)+order;					
				}
				

				//number = number+1; //20230630001
				//DecimalFormat df = new DecimalFormat("S00000000000"); 
				//no = df.format(number); //S20230630001
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}

	public int getPurchaseSave(ProductsaleDto dto) {
		int result = 0;
		String query="insert into bike_김용석_productsale\r\n" + 
				"(d_no,p_no,d_state,c_id,purchase_way,price,purchase_date,d_email,d_address)\r\n" + 
				"values('"+dto.getD_no()+"','"+dto.getP_no()+"','"+dto.getD_state()+"','"+dto.getC_id()+"','"+dto.getPurchase_way()+"','"+dto.getPrice()+"',\r\n" + 
				"to_date('"+dto.getPurchase_date()+"','yyyy-mm-dd hh24:mi:ss'),'"+dto.getD_email()+"','"+dto.getD_address()+"')";
		
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


	public ArrayList<ProductsaleDto> getProductAdminList(int start, int end, String select, String search, String state) {
		ArrayList<ProductsaleDto> arr = new ArrayList<>();
		String query = /*
						"select d_no,p_no,c_id,d_state,to_char(purchase_date,'yyyy-mm-dd') as purchase_date,purchase_way from bike_김용석_productsale\r\n" + 
						"where "+select+" like '%"+search+"%'\r\n" + 
						"and d_state like '%"+state+"%'\r\n" + 
						"order by purchase_date desc";
					  */
					"select * from\r\n" + 
					"(select rownum as rnum, tbl.* from\r\n" + 
					"(select a.d_no,a.p_no,a.c_id,a.d_state,to_char(a.purchase_date,'yyyy-mm-dd') as purchase_date,a.purchase_way,b.product_name\r\n" + 
					"from bike_김용석_productsale a, bike_김용석_product b\r\n" + 
					"where a.p_no = b.no\r\n" + 
					"and "+select+" like '%"+search+"%'\r\n" + 
					"and d_state like '%"+state+"%'\r\n" + 
					"order by d_no desc)tbl)\r\n" + 
					"where rnum >= "+start+" and rnum <= "+end+"";	
				
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String d_no = rs.getString("d_no");
				String p_no = rs.getString("p_no");
				String c_id = rs.getString("c_id");
				String d_state = rs.getString("d_state");
				String purchase_date = rs.getString("purchase_date");
				String purchase_way = rs.getString("purchase_way");
				String product_name = rs.getString("product_name");
				
				ProductsaleDto dto = new ProductsaleDto(d_no, p_no, d_state, c_id, purchase_way, purchase_date, product_name);
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


	public int getTotalCount(String select, String search, String state) {
		int count = 0;
		String query = "select count(*) as count from bike_김용석_productsale\r\n" + 
				"where "+select+" like '%"+search+"%'\r\n" + 
				"and d_state like '%"+state+"%'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
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
	
	public int getTotalCount(String select, String search) {
		int count = 0;
		String query = "select count(*) as count from bike_김용석_productsale a, bike_김용석_product b\r\n" + 
				"where a.p_no = b.no\r\n" + 
				"and "+select+" like '%"+search+"%'" ;
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
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

	public ProductsaleDto getProductsaleView(String d_no) {
		ProductsaleDto dto = null;
		String query="select a.d_no,a.p_no,a.c_id,a.d_state,a.purchase_way,b.product_photo,b.product_name,to_char(a.d_date,'yyyy-mm-dd hh24:mi:ss')as d_date,\r\n" + 
				"to_char(a.purchase_date,'yyyy-mm-dd hh24:mi:ss')as purchase_date,a.price,a.d_email,a.d_address\r\n" + 
				"from bike_김용석_productsale a, bike_김용석_product b\r\n" + 
				"where a.p_no = b.no\r\n" + 
				"and d_no = '"+d_no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String p_no = rs.getString("p_no");
				String c_id = rs.getString("c_id");
				String d_state = rs.getString("d_state");
				String purchase_way = rs.getString("purchase_way");
				String purchase_date = rs.getString("purchase_date");
				String price = rs.getString("price");
				String d_email = rs.getString("d_email");
				String d_address = rs.getString("d_address");
				String product_photo = rs.getString("product_photo");
				String product_name = rs.getString("product_name");
				String d_date = rs.getString("d_date");
				
				dto = new ProductsaleDto(d_no, p_no, d_state, c_id, d_email, d_address, purchase_way, price, purchase_date,product_photo,product_name,d_date);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	public ProductsaleDto getProductsalePreView(String d_no) {
		ProductsaleDto dto = null;
		String query="select a.d_no, b.p_no from\r\n" + 
				"(select max(d_no) as d_no from bike_김용석_productsale\r\n" + 
				"where d_no < '"+d_no+"')a, bike_김용석_productsale b\r\n" + 
				"where a.d_no = b.d_no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String preD_no = rs.getString("d_no");
				String p_no = rs.getString("p_no");
				
				dto = new ProductsaleDto(preD_no, p_no);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dto;
	}


	public ProductsaleDto getProductsaleNextView(String d_no) {
		ProductsaleDto dto = null;
		String query="select a.d_no, b.p_no from\r\n" + 
				"(select min(d_no) as d_no from bike_김용석_productsale\r\n" + 
				"where d_no > '"+d_no+"')a, bike_김용석_productsale b\r\n" + 
				"where a.d_no = b.d_no";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String nextD_no = rs.getString("d_no");
				String p_no = rs.getString("p_no");
				
				dto = new ProductsaleDto(nextD_no, p_no);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dto;
	}


	public int getUpdateState(ProductsaleDto dto) {
		int result = 0;
		String query="update bike_김용석_productsale\r\n" + 
				"    set d_state = '"+dto.getD_state()+"',\r\n" + 
				"        d_date = to_date('"+dto.getD_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where d_no = '"+dto.getD_no()+"'";
		
		
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


	public ArrayList<ProductsaleDto> getOrderList(int start, int end, String select, String search, String c_id) {
		ArrayList<ProductsaleDto> arr = new ArrayList<>();
		String query="select * from\r\n" + 
				"(select rownum as rnum, tbl.* from\r\n" + 
				"(select a.d_no,a.p_no,b.product_name,b.product_photo,to_char(a.purchase_date,'yyyy-mm-dd')as purchase_date,a.c_id\r\n" + 
				"from bike_김용석_productsale a, bike_김용석_product b\r\n" + 
				"where a.p_no = b.no\r\n" + 
				"and "+select+" like '%"+search+"%'\r\n" + 
				"and a.c_id = '"+c_id+"'\r\n" + 
				"order by a.d_no desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String d_no = rs.getString("d_no");
				String p_no = rs.getString("p_no");
				String product_name = rs.getString("product_name");
				String product_photo = rs.getString("product_photo");
				String purchase_date = rs.getString("purchase_date");
				
				ProductsaleDto dto = new ProductsaleDto(d_no, p_no, c_id, purchase_date, product_photo, product_name);
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


	public ProductsaleDto getMyorderView(String d_no) {
		ProductsaleDto dto = null;
		String query = "select a.p_no,a.d_no,a.d_state,a.c_id,a.d_email,a.d_address,\r\n" + 
				"to_char(a.purchase_date,'yyyy-mm-dd hh24:mi:ss')as purchase_date,a.purchase_way,\r\n" + 
				"a.price,to_char(a.d_date,'yyyy-mm-dd hh24:mi:ss')as d_date,b.product_photo,b.product_name \r\n" + 
				"from bike_김용석_productsale a, bike_김용석_product b\r\n" + 
				"where a.p_no = b.no\r\n" + 
				"and a.d_no = '"+d_no+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				String p_no = rs.getString("p_no");
				String d_state = rs.getString("d_state");
				String c_id = rs.getString("c_id");
				String d_email = rs.getString("d_email");
				String d_address = rs.getString("d_address");
				String purchase_date = rs.getString("purchase_date");
				String purchase_way = rs.getString("purchase_way");
				String price = rs.getString("price");
				String d_date = rs.getString("d_date");
				String product_photo = rs.getString("product_photo");
				String product_name = rs.getString("product_name");
				
				dto = new ProductsaleDto(d_no, p_no, d_state, c_id, d_email, d_address, purchase_way, price, purchase_date, product_photo, product_name, d_date);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	public String getMyorderPreView(String d_no) {
		String preD_no = "";
		String query = "select a.d_no from\r\n" + 
				"(select max(d_no) as d_no from bike_김용석_productsale\r\n" + 
				"where d_no < '"+d_no+"') a , bike_김용석_productsale b\r\n" + 
				"where a.d_no = b.d_no";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				preD_no = rs.getString("d_no");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return preD_no;
	}


	public String getMyorderNextView(String d_no) {
		String nextD_no = "";
		String query = "select a.d_no from\r\n" + 
				"(select min(d_no)as d_no from bike_김용석_productsale\r\n" + 
				"where d_no > '"+d_no+"') a, bike_김용석_productsale b\r\n" + 
				"where a.d_no = b.d_no";
		

		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				nextD_no = rs.getString("d_no");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return nextD_no;
	}
	
	
	
}
