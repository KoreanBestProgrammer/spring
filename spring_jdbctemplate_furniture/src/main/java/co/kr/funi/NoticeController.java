package co.kr.funi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping("Notice")
	public String notice(HttpServletRequest request) {
		
		String gubun = request.getParameter("t_gubun");
		
		if(gubun == null) gubun = "list";
		
		String viewPage = "";
		
		if(gubun.equals("list")) {
			
			viewPage = "notice/notice_list";
		}
		
		
		
		
		return viewPage;
			
	}
}
