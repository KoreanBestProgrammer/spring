package dto;

public class MemberDto {
	private String id,name,password,area,address,mobile_1,mobile_2,mobile_3,gender,hobby_travel,hobby_reading,hobby_sports,reg_date,update_date,last_login_date,exit_date,memberLevel;
	private int password_len;
	
	public MemberDto(){}
	
	
	
	public MemberDto(String id) {
		super();
		this.id = id;
	}



	//회원가입
		public MemberDto(String id, String name, String password, int password_len, String area, String address, String mobile_1,
				String mobile_2, String mobile_3, String gender, String hobby_travel, String hobby_reading,
				String hobby_sports, String reg_date) {
			super();
			this.id = id;
			this.name = name;
			this.password = password;
			this.password_len = password_len; 
			this.area = area;
			this.address = address;
			this.mobile_1 = mobile_1;
			this.mobile_2 = mobile_2;
			this.mobile_3 = mobile_3;
			this.gender = gender;
			this.hobby_travel = hobby_travel;
			this.hobby_reading = hobby_reading;
			this.hobby_sports = hobby_sports;
			this.reg_date = reg_date;
				
		}
	//로그인
		public MemberDto(String name, String memberLevel, String exit_date) {
			super();
			this.name = name;
			this.memberLevel = memberLevel;
			this.exit_date = exit_date;
		}
	//상제정보조회	(my info)		
	public MemberDto(String id, String name, String password, String area, String address, String mobile_1,
			String mobile_2, String mobile_3, String gender, String hobby_travel, String hobby_reading,
			String hobby_sports, String reg_date, String update_date, String last_login_date, String exit_date,
			String memberLevel, int password_len) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.area = area;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.gender = gender;
		this.hobby_travel = hobby_travel;
		this.hobby_reading = hobby_reading;
		this.hobby_sports = hobby_sports;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.last_login_date = last_login_date;
		this.exit_date = exit_date;
		this.memberLevel = memberLevel;
		this.password_len = password_len;
	}
	
//업데이트	(내정보 수정)
	public MemberDto(String id, String name, String area, String address, String mobile_1, String mobile_2,
			String mobile_3, String gender, String hobby_travel, String hobby_reading, String hobby_sports,
			String update_date) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.gender = gender;
		this.hobby_travel = hobby_travel;
		this.hobby_reading = hobby_reading;
		this.hobby_sports = hobby_sports;
		this.update_date = update_date;
		
	}
	
	
	
	public MemberDto(String id, String name, String area, String mobile_1, String mobile_2, String mobile_3,
			String reg_date, String last_login_date, String exit_date) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.reg_date = reg_date;
		this.last_login_date = last_login_date;
		this.exit_date = exit_date;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getArea() {
		return area;
	}
	public String getAddress() {
		return address;
	}
	public String getMobile_1() {
		return mobile_1;
	}
	public String getMobile_2() {
		return mobile_2;
	}
	public String getMobile_3() {
		return mobile_3;
	}
	public String getGender() {
		return gender;
	}
	public String getHobby_travel() {
		return hobby_travel;
	}
	public String getHobby_reading() {
		return hobby_reading;
	}
	public String getHobby_sports() {
		return hobby_sports;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public String getLast_login_date() {
		return last_login_date;
	}
	public String getExit_date() {
		return exit_date;
	}
	public int getPassword_len() {
		return password_len;
	}
	public String getMemberLevel() {
		return memberLevel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobile_1(String mobile_1) {
		this.mobile_1 = mobile_1;
	}

	public void setMobile_2(String mobile_2) {
		this.mobile_2 = mobile_2;
	}

	public void setMobile_3(String mobile_3) {
		this.mobile_3 = mobile_3;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHobby_travel(String hobby_travel) {
		this.hobby_travel = hobby_travel;
	}

	public void setHobby_reading(String hobby_reading) {
		this.hobby_reading = hobby_reading;
	}

	public void setHobby_sports(String hobby_sports) {
		this.hobby_sports = hobby_sports;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public void setExit_date(String exit_date) {
		this.exit_date = exit_date;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public void setPassword_len(int password_len) {
		this.password_len = password_len;
	}
	
	
	
}
