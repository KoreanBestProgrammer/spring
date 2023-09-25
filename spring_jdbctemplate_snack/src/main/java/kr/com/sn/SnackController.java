package kr.com.sn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.snack.DBSnackSave;
import command.snack.DBSnackUpdate;
import command.snack.SnackDelete;
import command.snack.SnackList;
import command.snack.SnackSave;
import command.snack.SnackUpdate;
import command.snack.SnackView;
import common.CommonTemplate;


@Controller
public class SnackController {
	
	
	
	@Autowired 
	JdbcTemplate template;
	
	@Autowired  
	public void setTemplate() {
		CommonTemplate.setTemplate(template);
	}	
		
		@RequestMapping("Snack")
		public String snack(HttpServletRequest request) {
			
			String gubun = request.getParameter("t_gubun");
			
			if(gubun == null) gubun = "list";
		
			String viewPage = "";
			
			//리스트
			if(gubun.equals("list")) {
				SnackList snack = new SnackList();
				snack.execute(request);
				viewPage = "/snack/snack_list";
			//등록폼	
			}else if(gubun.equals("writeForm")) {
				SnackSave snack = new SnackSave();
				snack.execute(request);
				viewPage = "/snack/snack_write";
			//등록	
			}else if(gubun.equals("snackSave")) {
				DBSnackSave snack = new DBSnackSave();
				snack.execute(request);
				viewPage = "/common_alert";
			//상세보기	
			}else if(gubun.equals("snackView")) {
				SnackView snack = new SnackView();
				snack.execute(request);
				viewPage = "/snack/snack_view";
			//수정폼
			}else if(gubun.equals("goUpdateForm")) {
				SnackUpdate snack = new SnackUpdate();
				snack.execute(request);
				viewPage = "/snack/snack_update";
			//수정	
			}else if(gubun.equals("snackUpdate")) {
				DBSnackUpdate snack = new DBSnackUpdate();
				snack.execute(request);
				viewPage = "/common_alert";
			//삭제	
			}else if(gubun.equals("snackDelete")) {
				SnackDelete snack = new SnackDelete();
				snack.execute(request);
				viewPage = "/common_alert";
			}
		
			
			
			
			return viewPage;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
}
