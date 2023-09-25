package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.CommonJDBCTemplate;
import dto.MemberDto;

public class MemberDao {
	 JdbcTemplate temp = CommonJDBCTemplate.getTemplate();
			 
	//비밀번호 변경 저장
		public int passwordUpdate(String id, String newPw) {
			String query = "update furni_김용석_member\r\n" + 
					"set password = '"+newPw+"'\r\n" + 
					"where id = '"+id+"'";
			
			
			return temp.update(query);
		}	
		
		//비밀번호 변경시 현재 비밀번호 확인
		public int nowPasswordConfirm(String id, String nowPw) {
			String query ="select count(*) as count\r\n" + 
					"from furni_김용석_member\r\n" + 
					"where id ='"+id+"'\r\n" + 
					"and password ='"+nowPw+"'";
			int count = temp.queryForObject(query, Integer.class);
			return count;
		}
	 
	//id중복검사
		public int checkId(String id) {
			int count = 0;
			String query = "select count(*) as count from furni_김용석_member\r\n" + 
						   "where id = '"+id+"'";
			
			count = temp.queryForObject(query, Integer.class);
			
			return count;
		}
	 
	 
		//회원가입
		public int getMemberSave(MemberDto dto) {
			int result = 0;
			String query="insert into furni_김용석_member\r\n" + 
					"(id,name,password,password_len,area,address,mobile_1,mobile_2,mobile_3\r\n" + 
					",gender,hobby_travel,\r\n" + 
					"hobby_reading,hobby_sports,reg_date)\r\n" + 
					"values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getPassword()+"',"+dto.getPassword_len()+",'"+dto.getArea()+"','"+dto.getAddress()+"','"+dto.getMobile_1()+"','"+dto.getMobile_2()+"','"+dto.getMobile_3()+"'\r\n" + 
					",'"+dto.getGender()+"','"+dto.getHobby_travel()+"','"+dto.getHobby_reading()+"','"+dto.getHobby_sports()+"',to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
					")";
		
			result = temp.update(query);
			
			return result;
		}
	 
		//암호화
				public String encryptSHA256(String value) throws NoSuchAlgorithmException{
			        String encryptData ="";
			        
			        MessageDigest sha = MessageDigest.getInstance("SHA-256");
			        sha.update(value.getBytes());
			 
			        byte[] digest = sha.digest();
			        for (int i=0; i<digest.length; i++) {
			            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
			        }
			     
			        return encryptData;
			    } 
				
				//로그인 이름 가져오기
				public String checkLogin(String id, String password) {
					String query = "select name from furni_김용석_member\r\n" + 
							"where id = '"+id+"'\r\n" + 
							"and password = '"+password+"'"+
							"and exit_date is null";
					
					String name = "";
					try {
						name = temp.queryForObject(query, String.class);
					}catch(Exception e) {
						
					}
					return name;
				}
	 
				public MemberDto getMemberInfo(String id) {
					String query = "select id,name,password,password_len,area,address,mobile_1,mobile_2,mobile_3,\r\n" + 
							"gender,hobby_travel,\r\n" + 
							"hobby_reading,hobby_sports,\r\n" + 
							"to_char(reg_date,'yyyy-mm-dd hh24:mi:ss') as reg_date,\r\n" + 
							"to_char(update_date,'yyyy-mm-dd hh24:mi:ss') as update_date,\r\n" + 
							"to_char(last_login_date,'yyyy-mm-dd hh24:mi:ss') as last_login_date,\r\n" + 
							"to_char(exit_date,'yyyy-mm-dd hh24:mi:ss') as exit_date\r\n" + 
							"from furni_김용석_member\r\n" + 
							"where id = '"+id+"'";
					
					
					RowMapper<MemberDto> memDto = new BeanPropertyRowMapper<MemberDto>(MemberDto.class); 
					MemberDto dto = temp.queryForObject(query, memDto);
					return dto;
				}
				
				public int getLastLogin(String id, String todayTime) {
					String query = "update furni_김용석_member\r\n" + 
							"    set last_login_date = to_date('"+todayTime+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
							"where id = '"+id+"'";
					
				
					return temp.update(query);
				}

				public int getPasswordCheck(String id, String pw) {
					String query = "select count(*) as count from furni_김용석_member\r\n" + 
							"where id = '"+id+"'\r\n" + 
							"and password = '"+pw+"'";
					
				
					
					return temp.queryForObject(query, Integer.class);
				}

				
				public int memberUpdate(MemberDto dto) {
					String query="update furni_김용석_member\r\n" + 
							"set name='"+dto.getName()+"',\r\n" + 
							"    area='"+dto.getArea()+"',\r\n" + 
							"    address='"+dto.getAddress()+"',\r\n" + 
							"    mobile_1='"+dto.getMobile_1()+"',\r\n" + 
							"    mobile_2='"+dto.getMobile_2()+"',\r\n" + 
							"    mobile_3='"+dto.getMobile_3()+"',\r\n" + 
							"    gender='"+dto.getGender()+"',\r\n" + 
							"    hobby_travel='"+dto.getHobby_travel()+"',\r\n" + 
							"    hobby_reading='"+dto.getHobby_reading()+"',\r\n" + 
							"    hobby_sports='"+dto.getHobby_sports()+"',\r\n" + 
							"    update_date=to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
							"where id='"+dto.getId()+"'";
					
				
					return temp.update(query);
				}
				
				public int getMemberExit(String id, String exit_date) {
					String query = "update furni_김용석_member\r\n" + 
							"set exit_date = to_date('"+exit_date+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
							"where id = '"+id+"'";
					
					return temp.update(query);
				}
				
/*	
	
	//회원가입
	public int getMemberSave(MemberDto dto) {
		int result = 0;
		String query="insert into bike_김용석_member\r\n" + 
				"(id,name,password,password_len,area,address,mobile_1,mobile_2,mobile_3\r\n" + 
				",gender,hobby_travel,\r\n" + 
				"hobby_reading,hobby_sports,reg_date)\r\n" + 
				"values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getPassword()+"',"+dto.getPassword_len()+",'"+dto.getArea()+"','"+dto.getAddress()+"','"+dto.getMobile_1()+"','"+dto.getMobile_2()+"','"+dto.getMobile_3()+"'\r\n" + 
				",'"+dto.getGender()+"','"+dto.getHobby_travel()+"','"+dto.getHobby_reading()+"','"+dto.getHobby_sports()+"',to_date('"+dto.getReg_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				")";
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
	//id중복검사
	public int checkId(String id) {
		int count = 0;
		String query = "select count(*) as count from bike_김용석_member\r\n" + 
					   "where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
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
	//로그인 이름 가져오기
	public MemberDto checkLogin(String id, String password) {
		MemberDto dto = null;
		String query = "select name,memberlevel,exit_date from bike_김용석_member\r\n" + 
				"where id = '"+id+"'\r\n" + 
				"and password = '"+password+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				String memberLevel = rs.getString("memberlevel");
				String name = rs.getString("name");
				String exit_date = rs.getString("exit_date");
				dto = new MemberDto(name,memberLevel,exit_date);
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}

	public int getLastLogin(String id, String todayTime) {
		int result = 0;
		String query = "update bike_김용석_member\r\n" + 
				"    set last_login_date = to_date('"+todayTime+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where id = '"+id+"'";
		
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

	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String query = "select \r\n" + 
				"id,name,password,password_len,area,address,mobile_1,mobile_2,mobile_3,\r\n" + 
				"gender,hobby_travel,\r\n" + 
				"hobby_reading,hobby_sports,\r\n" + 
				"to_char(reg_date,'yyyy-mm-dd hh24:mi:ss') as reg_date,\r\n" + 
				"to_char(update_date,'yyyy-mm-dd hh24:mi:ss') as update_date,\r\n" + 
				"to_char(last_login_date,'yyyy-mm-dd hh24:mi:ss') as last_login_date,\r\n" + 
				"to_char(exit_date,'yyyy-mm-dd hh24:mi:ss') as exit_date,\r\n" + 
				"memberlevel\r\n" + 
				"from bike_김용석_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");	
				String area = rs.getString("area");
				String address = rs.getString("address");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String gender = rs.getString("gender");
				String hobby_travel = rs.getString("hobby_travel");
				String hobby_reading = rs.getString("hobby_reading");
				String hobby_sports = rs.getString("hobby_sports");
				String reg_date = rs.getString("reg_date");
				String update_date = rs.getString("update_date");
				String last_login_date = rs.getString("last_login_date");
				String exit_date = rs.getString("exit_date");
				String memberLevel = rs.getString("memberlevel");
				int password_len = rs.getInt("password_len");
				
				dto = new MemberDto(id,name,password,area,address,mobile_1,mobile_2,mobile_3,gender,hobby_travel,hobby_reading,hobby_sports,reg_date,update_date,last_login_date,exit_date,memberLevel,password_len);
				
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}

	public int getPasswordCheck(String id, String pw) {
		int count = 0;
		String query = "select count(*) as count from bike_김용석_member\r\n" + 
				"where id = '"+id+"'\r\n" + 
				"and password = '"+pw+"'";
		
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}

	public int memberUpdate(MemberDto dto) {
		int result = 0;
		String query="update bike_김용석_member\r\n" + 
				"set name='"+dto.getName()+"',\r\n" + 
				"    area='"+dto.getArea()+"',\r\n" + 
				"    address='"+dto.getAddress()+"',\r\n" + 
				"    mobile_1='"+dto.getMobile_1()+"',\r\n" + 
				"    mobile_2='"+dto.getMobile_2()+"',\r\n" + 
				"    mobile_3='"+dto.getMobile_3()+"',\r\n" + 
				"    gender='"+dto.getGender()+"',\r\n" + 
				"    hobby_travel='"+dto.getHobby_travel()+"',\r\n" + 
				"    hobby_reading='"+dto.getHobby_reading()+"',\r\n" + 
				"    hobby_sports='"+dto.getHobby_sports()+"',\r\n" + 
				"    update_date=to_date('"+dto.getUpdate_date()+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where id='"+dto.getId()+"'";
		
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

	public int getMemberExit(String id, String exit_date) {
		int result = 0;
		String query = "update bike_김용석_member\r\n" + 
				"set exit_date = to_date('"+exit_date+"','yyyy-mm-dd hh24:mi:ss')\r\n" + 
				"where id = '"+id+"'";
		
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
	//비밀번호 찾기 확인용
	public String getCheckMember(String id, String mobile_1, String mobile_2, String mobile_3) {
		String name = "";
		String query = "select name from bike_김용석_member\r\n" + 
				"where id = '"+id+"'\r\n" + 
				"and mobile_1 = '"+mobile_1+"'\r\n" + 
				"and mobile_2 = '"+mobile_2+"'\r\n" + 
				"and mobile_3 = '"+mobile_3+"'";
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
		}catch(Exception e) {
			System.out.println("쿼리오류"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}	
		
		return name;
	}
	//암호화
		public String encryptSHA256(String value) throws NoSuchAlgorithmException{
	        String encryptData ="";
	        
	        MessageDigest sha = MessageDigest.getInstance("SHA-256");
	        sha.update(value.getBytes());
	 
	        byte[] digest = sha.digest();
	        for (int i=0; i<digest.length; i++) {
	            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
	        }
	     
	        return encryptData;
	    } 
	
	새로운 비밀번호 생성
		public String getNewPassword(int pwLength) {
	        StringBuffer temp =new StringBuffer();
	        Random rnd = new Random();
	        
	        for(int i=0;i<pwLength;i++)
	        {
	            int rIndex = rnd.nextInt(3);
	            switch (rIndex) {
	            case 0:
	                // a-z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                break;
	            case 1:
	                // A-Z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                break;
	            case 2:
	                // 0-9
	                temp.append((rnd.nextInt(10)));
	                break;
	            }
	//            System.out.println("pw :"+temp.toString());	
	        }
	        return temp.toString();		
		}
		//비밀번호 초기화 메일발송, 비밀번호 수정
		public int setMemberPassword(String id, String newPassword, int pwLeng) {
			int result = 0;
			String query = "update bike_김용석_member\r\n" + 
					"set password = '"+newPassword+"',\r\n" + 
					"    password_len = "+pwLeng+"\r\n" + 
					"where id = '"+id+"'";
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
		public ArrayList<MemberDto> getMemberList(String select, String search, int start, int end) {
			ArrayList<MemberDto> arr = new ArrayList<>();
			String query = "select * from\r\n" + 
					"(select rownum as rnum, tbl.* from\r\n" + 
					"(select id,name,area,mobile_1,mobile_2,mobile_3,to_char(reg_date,'yyyy-mm-dd') as reg_date,\r\n" + 
					"to_char(last_login_date,'yyyy-mm-dd hh24:mi:ss') as last_login_date,\r\n" + 
					"to_char(exit_date,'yyyy-mm-dd hh24:mi:ss') as exit_date\r\n" + 
					"from bike_김용석_member\r\n" + 
					"where "+select+" like '%"+search+"%'\r\n" + 
					"order by reg_date desc) \r\n" + 
					"tbl )\r\n" + 
					"where rnum >= "+start+" and rnum <= "+end+"";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()){
					String id = rs.getString("id");
					String name = rs.getString("name");
					String area = rs.getString("area");
					String mobile_1 = rs.getString("mobile_1");
					String mobile_2 = rs.getString("mobile_2");
					String mobile_3 = rs.getString("mobile_3");
					String reg_date = rs.getString("reg_date");
					String last_login_date = rs.getString("last_login_date");
					String exit_date = rs.getString("exit_date");
					
					MemberDto dto = new MemberDto(id, name, area, mobile_1, mobile_2, mobile_3, reg_date, last_login_date, exit_date);
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
		public int getTotalCount(String select, String search) {
			int count = 0;
			String query = "select count(*) as count from bike_김용석_member\r\n" + 
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
		
*/		
		
}
