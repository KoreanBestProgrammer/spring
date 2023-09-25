package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.CommonTemplate;
import common.DBConnection;

import dto.SnackDto;

public class SnackDao {
	
	JdbcTemplate temp = CommonTemplate.getTemplate();
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public ArrayList<SnackDto> getCompanyList() {
		String query = "select m_code, m_name from commonsnack\r\n" + 
					   "order by m_name";
		
		RowMapper<SnackDto> snackDto = new BeanPropertyRowMapper<SnackDto>(SnackDto.class);
		ArrayList<SnackDto> arr = (ArrayList<SnackDto>) temp.query(query, snackDto);
		
		return arr;
	}
	
	public int getSaveSnack(SnackDto dto) {
		
		String query = "insert into H_김용석_SNACK\r\n" + 
					   "(p_code,p_name,m_code,price)\r\n" + 
					   "values('"+dto.getP_code()+"','"+dto.getP_name()+"','"+dto.getM_code()+"',"+dto.getPrice()+")";
		
		
		return temp.update(query);
	}
	
	public ArrayList<SnackDto> getSnackList(String gubun, String search, String m_code){
		String query = "";
		if(m_code.equals("0")){
			query = "select h.p_code,h.p_name,c.m_name,to_char(h.price,'999,999')as price \r\n" + 
					"from H_김용석_SNACK h ,commonsnack c\r\n" + 
					"where h.m_code = c.m_code\r\n" + 
					"and "+gubun+" like '%"+search+"%'";
		}else {
			query = "select h.p_code,h.p_name,c.m_name,to_char(h.price,'999,999')as price \r\n" + 
					"from H_김용석_SNACK h ,commonsnack c\r\n" + 
					"where h.m_code = c.m_code\r\n" + 
					"and "+gubun+" like '%"+search+"%'\r\n" + 
					"and c.m_code = '"+m_code+"'";
		}
		
		RowMapper<SnackDto> snackDto = new BeanPropertyRowMapper<SnackDto>(SnackDto.class);
		ArrayList<SnackDto> arr = (ArrayList<SnackDto>) temp.query(query, snackDto);
		
		return arr;
	}
	public SnackDto getSnackView(String p_code) {
		String query = "select h.p_code,h.p_name,c.m_code,c.m_name,to_char(h.price,'999,999')as price \r\n" + 
				"from H_김용석_SNACK h ,commonsnack c\r\n" + 
				"where h.m_code = c.m_code\r\n" + 
				"and h.p_code = '"+p_code+"'";
		
		RowMapper<SnackDto> snackDto = new BeanPropertyRowMapper<SnackDto>(SnackDto.class);
		SnackDto dto = temp.queryForObject(query, snackDto);
		
		return dto;
		
	}
	public int snackUpdate(SnackDto dto) {
		
		String query = "update h_김용석_snack\r\n" + 
				       "    set p_name='"+dto.getP_name()+"',\r\n" + 
				       "        price="+dto.getPrice()+",\r\n" + 
				       "        m_code='"+dto.getM_code()+"'\r\n" + 
				       "where p_code='"+dto.getP_code()+"'";
		
		
		
		return temp.update(query);
	}
	public int snackDelete(String p_code) {
		
		String query = "delete from H_김용석_SNACK\r\n" + 
					   "where p_code='"+p_code+"'";
		
	
			return temp.update(query);
		
	}
	public int checkPcode(String p_code) {
		
		String query = "select count(*) as count from H_김용석_SNACK\r\n" + 
					   "where p_code='"+p_code+"'";
		
	
		int count = temp.queryForInt(query);
		
		return count;
	}
	
}
