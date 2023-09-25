package co.kr.funi;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberExit;
import command.member.MemberJoin;
import command.member.MemberLogin;
import command.member.MemberLogout;
import command.member.MemberMyinfo;
import command.member.MemberPasswordUpdate;
import command.member.MemberUpdate;
import common.CommonExecute;
import dao.MemberDao;

@Controller
public class MemberController {
	
	@RequestMapping("Member")
	public String member(HttpServletRequest request) {
		
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun="login";		
		
		String viewPage = "";
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		if(gubun.equals("join")) {
			
			viewPage="member/member_join";
		}else if(gubun.equals("login")) {
			
			viewPage="member/member_login";
		}else if(gubun.equals("memberSave")) {
			CommonExecute member = new MemberJoin();
			member.execute(request);
			viewPage = "common_alert";
		}else if(gubun.equals("memberLogin")) {
			CommonExecute member = new MemberLogin();
			member.execute(request);
			viewPage = "common_alert";
		}else if(gubun.equals("logout")) {
			CommonExecute member = new MemberLogout();
			member.execute(request);
			viewPage = "common_alert";
		}else if(gubun.equals("myinfo")) {
			if(id == null) {
				request.setAttribute("t_msg", "세션정보가 만료되었습니다");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert";
			}else{
				CommonExecute member = new MemberMyinfo();
				member.execute(request);
				viewPage = "member/member_myinfo";
			}
		}else if(gubun.equals("updateForm")) {
			if(id == null) {
				request.setAttribute("t_msg", "세션정보가 만료되었습니다");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert";
			}else{
				CommonExecute member = new MemberMyinfo();
				member.execute(request);
				viewPage = "member/member_update";
			}
		}else if(gubun.equals("memberUpdate")) {
			CommonExecute member = new MemberUpdate();
			member.execute(request);
			viewPage = "common_alert";
		} else if(gubun.equals("passwordUpdateForm")){
			
			viewPage="member/password_update";
		}else if(gubun.equals("passwordUpdate")) {
			CommonExecute member = new MemberPasswordUpdate();
			member.execute(request);
			viewPage="common_alert";
		}else if(gubun.equals("memberExit")) {
			CommonExecute member = new MemberExit();
			member.execute(request);
			viewPage = "common_alert";
		}
		
		return viewPage;
	}
	
	
	@RequestMapping("MemberCheckId")
	public void memberCheckId(HttpServletRequest request,HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		
		int count = dao.checkId(id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(count == 0) {
			out.print("사용가능");
		}else {
			out.print("사용불가능");
		}
		
	}
	
	@RequestMapping("MemberCheckPassword")
	public void memberCheckPassword(HttpServletRequest request,HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_password");
		
		
		
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		
		String name = dao.checkLogin(id, password);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(name.equals("")) {
			out.print("no");
		}else {
			out.print("yes");
		}
	}

	
}
