package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberLogin implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");
		try {
			pw = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = dao.checkLogin(id, pw);
		
		String msg = "";
		String url = "";
		
		if(name.equals("")) {
			msg="ID나 비밀번호가 맞지 않습니다.";
			url="Member";
		}else {
			msg=name+"님 환영합니다.";
			url="/funi/";
			
			String loginTime = CommonUtil.getTodayTime();
			
			int result = dao.getLastLogin(id, loginTime);
			if(result != 1) {
				System.out.println("최종 로그인 시간 업데이트 오류");
			}
			HttpSession session = request.getSession();
			session.setAttribute("sessionId",id);
			session.setAttribute("sessionName", name);
			if(id.equals("manager")) {
				session.setAttribute("sessionLevel", "top");
			}
			session.setMaxInactiveInterval(60*60);
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}

}
