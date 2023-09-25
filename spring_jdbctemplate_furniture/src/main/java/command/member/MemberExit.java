package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberExit implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("sessionId");
		String exit_date = CommonUtil.getTodayTime();
		
		int result = dao.getMemberExit(id, exit_date);
		
		String msg = "탈퇴 되었습니다.";
		
		if(result == 1) {
			session.invalidate();
		}else {
			msg = "탈퇴 처리 오류";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "/funi/");
	}

}
