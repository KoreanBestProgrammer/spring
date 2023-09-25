package co.kr.mem;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import commad.member.Memberlist;
import common.CommonTemplate;

@Controller
public class MemberController {
	
	@Autowired 
	JdbcTemplate temp;
	
	@Autowired 
	public void setTemp(){
		CommonTemplate.setTemplate(temp);
	}
	
	
	@RequestMapping("Member")
	public String member(HttpServletRequest request) {
		String gubun = request.getParameter("t_gubun");
		
		if(gubun == null) {
			gubun = "list";
		}
		
		String viewPage = "";
		
		if(gubun.equals("list")) {
			Memberlist member = new Memberlist();
			member.excute(request);
			viewPage = "/member/member_list";
		}else if(gubun.equals("view")) {
			
			viewPage = "/member/member_view";
		}else if(gubun.equals("updateform")) {
			
			viewPage = "/member/member_update";
		}else if(gubun.equals("update")) {
			
			viewPage = "common_alert";
		}else if(gubun.equals("delete")) {
			
			viewPage = "common_alert";
		}else if(gubun.equals("writeform")) {
			
			viewPage = "/member/member_write";
		}else if(gubun.equals("save")) {
			
			viewPage = "common_alert";
		}
		
		
		return viewPage;
	}
	
	
	
	
	
	
	
}
