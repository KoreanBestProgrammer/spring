package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackView implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		
		String p_code = request.getParameter("t_p_code");
		
		SnackDto dto = dao.getSnackView(p_code);
		
		ArrayList<SnackDto> arr = dao.getCompanyList();
		
		
		
		
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_arr", arr);
	}

}
