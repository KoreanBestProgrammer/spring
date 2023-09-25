package command.member;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.Member_dao;
import dto.Member_dto;

public class MemberUpdate implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
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
	}

}
