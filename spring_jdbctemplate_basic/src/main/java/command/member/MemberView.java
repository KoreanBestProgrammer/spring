package command.member;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.Member_dao;
import dto.Member_dto;

public class MemberView implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id"); 
		
		Member_dto dto = dao.getMemberView(id);
		
		request.setAttribute("t_dto", dto);
		
	}

}
