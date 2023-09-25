package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		ArrayList<SnackDto> arr2 = dao.getCompanyList();
		request.setAttribute("t_arr2", arr2);
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		String m_code = request.getParameter("t_m_code");
		if(select == null) {
			select="h.p_name";
			search = "";
			m_code = "0";
		}
		ArrayList<SnackDto> arr = dao.getSnackList(select, search, m_code);	
		request.setAttribute("t_arr", arr);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		request.setAttribute("t_m_code", m_code);

	}

}
