package dto;

public class QnaDto {
	private String no,title,content,attach,reg_date,update_date,reg_id,answer,answer_id,state,
				   admin_update_date,answer_date;

	
	
	
	




	//답변수정,답변삭제
	public QnaDto(String no, String answer, String admin_update_date) {
		super();
		this.no = no;
		this.answer = answer;
		this.admin_update_date = admin_update_date;
	}




	//질문수정
	public QnaDto(String no, String title, String content, String attach, String update_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.update_date = update_date;
	}

	
	
	
	//답변등록
	public QnaDto(String no, String answer, String answer_id, String answer_date) {
		super();
		this.no = no;
		this.answer = answer;
		this.answer_id = answer_id;
		this.answer_date = answer_date;
	}





	//뷰
	public QnaDto(String no, String title, String content, String attach, String reg_date, String update_date,
			String reg_id, String answer, String answer_id, String admin_update_date, String answer_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.reg_id = reg_id;
		this.answer = answer;
		this.answer_id = answer_id;
		this.admin_update_date = admin_update_date;
		this.answer_date = answer_date;
	}

	//질문리스트
	public QnaDto(String no, String title, String attach, String reg_date, String reg_id, 
			String answer) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.reg_date = reg_date;
		this.reg_id = reg_id;
		this.answer = answer;
		
	}

	//질문등록
	public QnaDto(String no, String title, String content, String attach, String reg_date, String reg_id
			,String state) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.reg_id = reg_id;
		this.state = state;
		
		
	}

	
	
	public String getAdmin_update_date() {
		return admin_update_date;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getAttach() {
		return attach;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public String getReg_id() {
		return reg_id;
	}

	public String getAnswer() {
		return answer;
	}

	public String getAnswer_id() {
		return answer_id;
	}

	public String getState() {
		return state;
	}
	public String getAnswer_date() {
		return answer_date;
	}
	
	
	
}
