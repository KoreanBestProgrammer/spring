package com.kr.sts;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.snack.SnackDelete;
import command.snack.SnackList;
import command.snack.SnackSave;
import command.snack.SnackUpdate;
import command.snack.SnackView;
import command.snack.SnackWriteForm;

@Controller
public class Snack {
	
	@RequestMapping("Snack")
	public String Snack(HttpServletRequest request) {
		
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		
		if(gubun.equals("write")) {
			SnackWriteForm snack = new SnackWriteForm();
			snack.excute(request);
			viewPage = "/snack/snack_write";
			
		}else if(gubun.equals("list")) {
			SnackList snack = new SnackList();
			snack.excute(request);
			viewPage = "/snack/snack_list";
			
		}else if(gubun.equals("save")) {
			SnackSave snack = new SnackSave();
			snack.excute(request);
			viewPage = "/common_alert";
			
		}else if(gubun.equals("view")) {
			SnackView snack = new SnackView();
			snack.excute(request);
			viewPage = "/snack/snack_view";
			
		}else if(gubun.equals("updateform")) {
			SnackView snack = new SnackView();
			snack.excute(request);
			viewPage = "/snack/snack_update";
			
		}else if(gubun.equals("update")) {
			SnackUpdate snack = new SnackUpdate();
			snack.excute(request);
			viewPage = "/common_alert";
			
		}else if(gubun.equals("delete")) {
			SnackDelete snack = new SnackDelete();
			snack.excute(request);
			viewPage = "/common_alert";
			
		}
		
		
		return viewPage;
	}
}
