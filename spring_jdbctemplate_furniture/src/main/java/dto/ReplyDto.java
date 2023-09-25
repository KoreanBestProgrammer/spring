package dto;

public class ReplyDto {
	private String no,reply,reply_date,reply_name,noname,c_no,c_cno;

	
	
	
	public ReplyDto( String noname, String reply, String reply_date, String reply_name) {
		super();
		this.noname = noname;
		this.reply = reply;
		this.reply_date = reply_date;
		this.reply_name = reply_name;
		
	}

	public ReplyDto(String c_no, String no, String noname, String reply, String reply_date, String reply_name) {
		super();
		this.c_no = c_no;
		this.no = no;
		this.noname = noname;
		this.reply = reply;
		this.reply_date = reply_date;
		this.reply_name = reply_name;
		
	}

	
	public String getReply() {
		return reply;
	}

	public String getReply_date() {
		return reply_date;
	}

	public String getReply_name() {
		return reply_name;
	}
	public String getNo() {
		return no;
	}
	public String getNoname() {
		return noname;
	}
	public String getC_no() {
		return c_no;
	}
	public String getC_cno() {
		return c_cno;
	}
}
