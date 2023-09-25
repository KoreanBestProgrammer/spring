package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackList implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String gubun = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		
		if(gubun == null) {
			gubun = "p_name";
			search = "";
		}
		
		ArrayList<SnackDto> arr = dao.getSnackList(gubun, search);
		
		request.setAttribute("t_arr", arr);
		request.setAttribute("t_select", gubun);
		request.setAttribute("t_search", search);
	}

}
