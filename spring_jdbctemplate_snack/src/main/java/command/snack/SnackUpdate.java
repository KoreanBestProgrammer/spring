package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackUpdate implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		ArrayList<SnackDto> arr = dao.getCompanyList();
		request.setAttribute("t_arr", arr);
		
		String p_code = request.getParameter("t_p_code");
		
		SnackDto dto = dao.getSnackView(p_code);
		request.setAttribute("t_dto", dto);
		

	}

}
