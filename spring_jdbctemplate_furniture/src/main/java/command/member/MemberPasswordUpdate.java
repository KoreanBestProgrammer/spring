package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberPasswordUpdate implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = CommonUtil.getSessionId(request);
		String nowPw = request.getParameter("t_now_pw");
		String newPw = request.getParameter("t_new_pw");
		
		try {
			nowPw = dao.encryptSHA256(nowPw);
			newPw = dao.encryptSHA256(newPw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		int result = 0;
		String msg ="";
		int confirm = dao.nowPasswordConfirm(id, nowPw);
		if(confirm == 1) {
			result = dao.passwordUpdate(id, newPw);
			if(result == 1) {
				msg ="새로운 비밀번호로 변경되었습니다.";
				HttpSession session = request.getSession();
				session.invalidate();
			} else {
				msg ="비밀번호 변경 실패!";
			}
		} else {
			msg ="현재 비밀번호가 정확하지 않습니다.";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "/funi/");

		
		
	}

}
