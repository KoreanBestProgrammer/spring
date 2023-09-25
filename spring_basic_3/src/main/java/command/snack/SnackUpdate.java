package command.snack;

import javax.servlet.http.HttpServletRequest;

import common.commonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackUpdate implements commonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String p_code = request.getParameter("t_p_code");
		String p_name = request.getParameter("t_p_name");
		String price = request.getParameter("t_price");
		price = price.replaceAll(",", "");
		price = price.trim();
		String m_code = request.getParameter("t_m_code");
		
		SnackDto dto = new SnackDto(p_code, p_name, m_code, Integer.parseInt(price));
		
		int result = dao.snackUpdate(dto);
		
		String msg = result == 1? "수정성공!":"수정실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url","Snack");
				
				
		
		
	}

}
