package dao;


import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import common.CommonTemplate;
import dto.Member_dto;

public class Member_dao {

	JdbcTemplate temp = CommonTemplate.getTemplate();
	

	
	public int MemberSave(Member_dto dto) {
		String query = "insert into h_김용석_member\r\n" + 
				       "(id,name,age,reg_date)\r\n" + 
				       "values('"+dto.getId()+"','"+dto.getName()+"',"+dto.getAge()+",'"+dto.getReg_date()+"')";
		
	//	int result = temp.update(query);
		
		return temp.update(query);
	}
	
	public ArrayList<Member_dto> getMemberList(String select, String search) {
		String query = " select id,name,age,to_char(reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
			       " from H_김용석_MEMBER\r\n" + 
			       " where "+select+" like '%"+search+"%'\r\n" + 
			       " order by reg_date desc";
		
		RowMapper<Member_dto> memDto = new BeanPropertyRowMapper<Member_dto>(Member_dto.class);
		ArrayList<Member_dto> arr = (ArrayList<Member_dto>) temp.query(query, memDto);
		
		
		return arr;
	}
	
	public Member_dto getMemberView(String id) {
		String query = " select id,name,age,to_char(reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
				       " from H_김용석_MEMBER\r\n" + 
				       " where id = '"+id+"' "; 
				       
		RowMapper<Member_dto> memDto = new BeanPropertyRowMapper<Member_dto>(Member_dto.class);
		Member_dto dto = temp.queryForObject(query, memDto);
		
		return dto;
	}
	public int getUpdate(Member_dto dto) {
		String query = " update h_김용석_member\r\n" + 
					   	   "    set name ='"+dto.getName()+"',\r\n" + 
					   	   "        age = "+dto.getAge()+",\r\n" + 
					   	   "        reg_date = '"+dto.getReg_date()+"'\r\n" + 
					   	   " where id = '"+dto.getId()+"'";
			
		
		return temp.update(query);
	}
	public int getDelete(String id) {
		String query = "delete from h_김용석_member\r\n" + 
				       "where id = '"+id+"'";
		
		
		return temp.update(query);
	}
}
