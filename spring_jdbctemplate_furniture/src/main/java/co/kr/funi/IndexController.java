package co.kr.funi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonJDBCTemplate;

@Controller
public class IndexController {
	
	@Autowired
	public JdbcTemplate temp;
	
	@Autowired
	public void setTemplate() {
		CommonJDBCTemplate.setTemplate(temp);
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
}
