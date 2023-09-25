package com.kr.sts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberDelete;
import command.member.MemberList;
import command.member.MemberUpdate;
import command.member.MemberView;
import command.member.MemberWrite;
import dao.Member_dao;
import dto.Member_dto;

@Controller
public class Member {
	
	@RequestMapping("Member")
	public String Member(HttpServletRequest request) {
		
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
	}
	
	
	
	//목록
	@RequestMapping("memberList")
	public String memberList(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		
		if(select == null) {
			select = "id";
			search = "";
		}
		
		ArrayList<Member_dto> arr = dao.getMemberList(select, search);
		
		request.setAttribute("t_arr", arr);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		
		return "/member/member_list";
	}
	
	//등록
	@RequestMapping("memberSave")
	public String memberSave(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String age = request.getParameter("t_age");
		String reg_date = request.getParameter("t_reg_date");
		
		Member_dto dto = new Member_dto(id, name, Integer.parseInt(age), reg_date);
				
		int result = dao.MemberSave(dto);
		String msg = "등록성공!";
		if(result != 1) msg = "등록실패!";
			
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
		return "/common_alert";  // "/"는 views
	}
	
	//등록폼
	@RequestMapping("memberWrite")
	public String memberWrite() {
		
		return "/member/member_write";
	}
	
	@RequestMapping("memberView")
	public String memberView(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 
		
		Member_dto dto = dao.getMemberView(id);
		
		request.setAttribute("t_dto", dto);
		
		return "/member/member_view";
	}
	
	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 

		Member_dto dto = dao.getMemberView(id);
		
		request.setAttribute("t_dto", dto);
		
		return "/member/member_update";
	}
	
	
	@RequestMapping("memberUpdate")
	public String memberUpdate(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 
		String name= request.getParameter("t_name");
		String age = request.getParameter("t_age");
		String reg_date = request.getParameter("t_reg_date");
		
		Member_dto dto = new Member_dto(id, name, Integer.parseInt(age), reg_date);
		
		int result = dao.getUpdate(dto);
		
		String msg = "수정성공!";
		if(result != 1) msg = "수정실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
		return "/common_alert";
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 
		
		int result = dao.getDelete(id);
		
		String msg = "삭제성공!";
		if(result != 1) msg = "삭제실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
		return "/common_alert";
	}
}
