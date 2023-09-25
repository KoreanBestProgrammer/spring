package dto;

public class EtcDto {
	private String no,title,content,reg_id,reg_name,reg_date,reply,reply_date;

	
	
	
	public EtcDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}
	public EtcDto(String no, String title, String content, String reg_name, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
	}
	//리스트
	public EtcDto(String no, String title, String reg_name, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
	}
	//등록
	public EtcDto(String no, String title, String content, String reg_id, String reg_name, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
	}

	public String getReg_name() {
		return reg_name;
	}
	
	public String getNo() {
		return no;
	}

	public String getContent() {
		return content;
	}

	public String getReply() {
		return reply;
	}

	public String getReply_date() {
		return reply_date;
	}

	public String getTitle() {
		return title;
	}

	public String getReg_id() {
		return reg_id;
	}

	public String getReg_date() {
		return reg_date;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
