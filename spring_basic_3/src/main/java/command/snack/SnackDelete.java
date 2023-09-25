package command.snack;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.SnackDao;

public class SnackDelete implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String p_code = request.getParameter("t_p_code");
		
		int result = dao.snackDelete(p_code);
		
		String msg = result == 1? "삭제성공!":"삭제실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url","Snack");
		
	}

}
