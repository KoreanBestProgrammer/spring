package command.member;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.Member_dao;

public class MemberDelete implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 
		
		int result = dao.getDelete(id);
		
		String msg = result == 1? "삭제성공!":"삭제실패!"; 
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
	}

}
