package com.kr.track;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberDelete;
import command.member.MemberList;
import command.member.MemberUpdate;
import command.member.MemberView;
import command.member.MemberWrite;
import common.CommonTemplate;
import dto.Member_dto;
	

@Controller
public class MemberController {
	
	@Autowired 
	Member_dto memDto;
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired  
	public void setTemplate() {
		CommonTemplate.setTemplate(template);
	}
	
	
	
	 
	
	
	
	@RequestMapping("Member") 
	public String member(HttpServletRequest request) {
	
		String gubun = request.getParameter("t_gubun");
		
		if(gubun == null) gubun = "list";
		String viewPage = "";
		
		if(gubun.equals("list")) {
			MemberList member = new MemberList();
			member.excute(request);
			viewPage = "/memberMvc/member_list";
			
		}else if(gubun.equals("writeform")) {
			
			viewPage = "/memberMvc/member_write";
			
		}else if(gubun.equals("save")) {
			MemberWrite member = new MemberWrite();
			member.excute(request);
			viewPage = "/common_alert";
			
		}else if(gubun.equals("view")) {
			MemberView member = new MemberView();
			member.excute(request);
			viewPage = "/memberMvc/member_view";
			
		}else if(gubun.equals("updateform")) {
			MemberView member = new MemberView();
			member.excute(request);
			viewPage = "/memberMvc/member_update";
			
		}else if(gubun.equals("update")) {
			MemberUpdate member = new MemberUpdate();
			member.excute(request);
			viewPage = "/common_alert";
			
		}else if(gubun.equals("delete")) {
			MemberDelete member = new MemberDelete();
			member.excute(request);
			viewPage = "/common_alert";
		}
		
		
		return viewPage;
		
/*		String query = " select id,name,age,to_char(reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
			       " from H_김용석_MEMBER\r\n" + 
			       " where id = '101' "; 
		
		RowMapper<Member_dto> memDto = new BeanPropertyRowMapper<Member_dto>(Member_dto.class);
		
		Member_dto dto = template.queryForObject(query, memDto);
		
		System.out.println("id :"+dto.getId());
		System.out.println("name :"+dto.getName());
		System.out.println("reg_date :"+dto.getReg_date());
		System.out.println("age :"+dto.getAge());
		
		query = " select id,name,age,to_char(reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
			       " from H_김용석_MEMBER\r\n" + 
			       " order by reg_date desc";
		
		RowMapper<Member_dto> memDtoList = new BeanPropertyRowMapper<Member_dto>(Member_dto.class);
		
		ArrayList<Member_dto> arr = (ArrayList<Member_dto>) template.query(query, memDtoList);
		
		
		System.out.println("회원수 : "+arr.size());
		
		query = "delete from h_김용석_member\r\n" + 
			       "where id = '101'";
		int result = template.update(query);
		
		System.out.println("삭제 : "+result);
		
		
		return "home";*/
	}
	
}
