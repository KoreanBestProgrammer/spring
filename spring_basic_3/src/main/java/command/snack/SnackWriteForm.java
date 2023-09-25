package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackWriteForm implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		ArrayList<SnackDto> arr = dao.getCompanyList();
		
		request.setAttribute("t_arr", arr);
	}

}
