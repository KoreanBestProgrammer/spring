package commad.member;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.Member_dao;
import dto.Member_dto;

public class Memberlist implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		
		String search = request.getParameter("t_search");
		String select = request.getParameter("t_select");
		if(select == null) {
			select = "id";
			search = "";
		}
		
		List<Member_dto> arr = dao.getMemberList(select, search);
		
		
		
		
		request.setAttribute("t_arr",arr);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		
	}

}
